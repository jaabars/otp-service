package kg.itschoolmegacom.otpservice.controllers;

import kg.itschoolmegacom.otpservice.models.dtos.UserDto;
import kg.itschoolmegacom.otpservice.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/save")
  public UserDto save(@RequestBody UserDto userDto){
    return userService.save(userDto);
  }
}
