package kg.itschoolmegacom.otpservice.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kg.itschoolmegacom.otpservice.exceptions.UserNotFoundException;
import kg.itschoolmegacom.otpservice.mappers.UserMapper;
import kg.itschoolmegacom.otpservice.models.dtos.CodeDto;
import kg.itschoolmegacom.otpservice.models.dtos.requests.AuthDto;
import kg.itschoolmegacom.otpservice.models.entities.User;
import kg.itschoolmegacom.otpservice.models.enums.Status;
import kg.itschoolmegacom.otpservice.repositrories.UserRepo;
import kg.itschoolmegacom.otpservice.services.AuthService;
import kg.itschoolmegacom.otpservice.services.CodeService;
import kg.itschoolmegacom.otpservice.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

  private UserRepo userRepo;
  private MailService mailService;
  private CodeService codeService;

  private final long OTP_TTL = 60_000l;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Value("${jwt.token.expired}")
  private String TOKEN_TTL;

  @Value("${jwt.token.secret}")
  private String secret;


  @Autowired
  public AuthServiceImpl(UserRepo userRepo, MailService mailService, CodeService codeService) {
    this.userRepo = userRepo;
    this.mailService = mailService;
    this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    this.codeService = codeService;
  }

  @PostConstruct
  public void initSecret() {
    secret = Base64.getEncoder().encodeToString(secret.getBytes());
  }


  @Override
  public ResponseEntity<?> generateOtp(AuthDto authDto) {
    User user = userRepo.findByEmail(authDto.getEmail());

    if (Objects.isNull(user)) {
      user = new User();
      user.setEmail(authDto.getEmail());
      user = userRepo.save(user);
    } else if (user.getEndBlockDate() != null && user.getEndBlockDate().after(new Date())) {
      return new ResponseEntity<>("Пользователь заблокирован", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    CodeDto olCode = codeService.findUserCode(user);

    if (Objects.nonNull(olCode)) {
      olCode.setStatus(Status.CANCELLED);
      olCode.setEndDate(new Date());
      codeService.update(olCode);
    }

    CodeDto newCode = new CodeDto();
    Random random = new Random();
    int otp = random.nextInt(9999 - 1000) + 1000;

    String hashedOtp = bCryptPasswordEncoder.encode(String.valueOf(otp));

    newCode.setCode(hashedOtp);
    newCode.setEndDate(new Date(System.currentTimeMillis() + OTP_TTL));
    newCode.setStatus(Status.NEW);
    newCode.setUser(UserMapper.INSTANCE.userToUserDto(user));

    codeService.save(newCode);

    mailService.sendEmail(user.getEmail(), "Your code is " + otp, "OTP code");

    return ResponseEntity.ok("Ваш код отправлен на email");
  }

  @Override
  public ResponseEntity<?> verify(AuthDto authDto) {
    User user = userRepo.findByEmail(authDto.getEmail());

    if (Objects.isNull(user)) {
      throw new UserNotFoundException("User not found");
    }


    Claims claims = Jwts.claims().setSubject(user.getEmail());

    Date now = new Date();
    Date expirationDate = new Date(now.getTime() + Long.valueOf(TOKEN_TTL));

    String token = Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();

    return ResponseEntity.ok(token);
  }

  @Override
  public ResponseEntity<?> authenticateToken(String token) {

    String userName = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();

    return ResponseEntity.ok(userName);
  }
}
