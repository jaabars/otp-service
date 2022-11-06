package kg.itschoolmegacom.otpservice.services.impl;

import kg.itschoolmegacom.otpservice.mappers.UserMapper;
import kg.itschoolmegacom.otpservice.models.dtos.UserDto;
import kg.itschoolmegacom.otpservice.models.entities.User;
import kg.itschoolmegacom.otpservice.repositrories.UserRepo;
import kg.itschoolmegacom.otpservice.services.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

  private UserRepo userRepo;

  public UserServiceImpl(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public UserDto save(UserDto userDto) {
    User user = UserMapper.INSTANCE.userDtoToUser(userDto);
    user = userRepo.save(user);
    return UserMapper.INSTANCE.userToUserDto(user);
  }
}
