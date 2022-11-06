package kg.itschoolmegacom.otpservice.models.dtos;

import kg.itschoolmegacom.otpservice.models.enums.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CodeDto {
  Long id;
  String code;
  Date endDate;
  Status status;
  Integer attemptCount;
  UserDto user;
}
