package kg.itschoolmegacom.otpservice.models.entities;

import kg.itschoolmegacom.otpservice.models.enums.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Code {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "code_seq")
  @SequenceGenerator(name = "code_seq", sequenceName = "code_sequence")
  Long id;
  String code;
  Date endDate;
  @Enumerated(value = EnumType.STRING)
  Status status;
  Integer attemptCount;

  @ManyToOne
  @JoinColumn(name = "user_id")
  User user;
}
