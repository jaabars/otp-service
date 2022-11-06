package kg.itschoolmegacom.otpservice.models.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyToOne;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
  Long id;
  String email;
  String code;
  Date createdDate;
  Date otpCodeEndDate;
  Date endDate;
}
