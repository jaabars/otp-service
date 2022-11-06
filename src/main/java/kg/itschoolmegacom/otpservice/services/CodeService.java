package kg.itschoolmegacom.otpservice.services;

import kg.itschoolmegacom.otpservice.models.dtos.CodeDto;
import kg.itschoolmegacom.otpservice.models.entities.User;

public interface CodeService {
  CodeDto findUserCode(User user);

  CodeDto update(CodeDto code);

  CodeDto save(CodeDto newCode);
}
