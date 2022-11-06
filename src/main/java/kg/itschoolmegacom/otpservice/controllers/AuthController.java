package kg.itschoolmegacom.otpservice.controllers;


import kg.itschoolmegacom.otpservice.models.dtos.requests.AuthDto;
import kg.itschoolmegacom.otpservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/otp")
  public ResponseEntity<?> generateOtp(@RequestBody AuthDto authDto){

    return authService.generateOtp(authDto);
  }

  @PostMapping("/verify")
  public ResponseEntity<?> verify(@RequestBody AuthDto authDto){
    return  authService.verify(authDto);
  }

  @GetMapping("/authenticate")
  public ResponseEntity<?> authenticate(@RequestHeader String token){
    return authService.authenticateToken(token);
  }
 }
