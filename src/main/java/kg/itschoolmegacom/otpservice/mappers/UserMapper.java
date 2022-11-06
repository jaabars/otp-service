package kg.itschoolmegacom.otpservice.mappers;

import kg.itschoolmegacom.otpservice.models.dtos.UserDto;
import kg.itschoolmegacom.otpservice.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User userDtoToUser(UserDto userDto);
  UserDto userToUserDto(User user);

  /*default User user(UserDto userDto){
    User user = new User();
    user.setCode(userDto.getCode());
    return user;
  }*/
}
