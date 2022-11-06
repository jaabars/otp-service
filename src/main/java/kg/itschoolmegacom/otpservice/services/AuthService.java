package kg.itschoolmegacom.otpservice.services;

import kg.itschoolmegacom.otpservice.models.dtos.requests.AuthDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
  ResponseEntity<?> generateOtp(AuthDto authDto);

  ResponseEntity<?> verify(AuthDto authDto);

  ResponseEntity<?> authenticateToken(String token);
}
