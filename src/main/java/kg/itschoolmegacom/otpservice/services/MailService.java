package kg.itschoolmegacom.otpservice.services;

public interface MailService {
  void sendEmail(String recepient, String text, String subject);
}
