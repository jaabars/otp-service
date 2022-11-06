package kg.itschoolmegacom.otpservice.repositrories;

import kg.itschoolmegacom.otpservice.models.entities.Code;
import kg.itschoolmegacom.otpservice.models.entities.User;
import kg.itschoolmegacom.otpservice.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepo extends JpaRepository<Code, Long> {
  Code findByUserAndStatusIs(User user, Status status);
}
