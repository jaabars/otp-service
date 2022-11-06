package kg.itschoolmegacom.otpservice.services;

import kg.itschoolmegacom.otpservice.models.dtos.UserDto;

public interface UserService {
  UserDto save(UserDto userDto);
}
