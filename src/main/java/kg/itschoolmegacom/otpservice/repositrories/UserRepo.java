package kg.itschoolmegacom.otpservice.repositrories;

import kg.itschoolmegacom.otpservice.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  User findByEmail(String email);
}
