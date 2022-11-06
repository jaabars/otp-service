package kg.itschoolmegacom.otpservice.services.impl;

import kg.itschoolmegacom.otpservice.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

  @Autowired
  private JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String sender;

  @Override
  public void sendEmail(String recepient, String text, String subject) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(sender);
    simpleMailMessage.setTo(recepient);
    simpleMailMessage.setText(text);
    simpleMailMessage.setSubject(subject);
    javaMailSender.send(simpleMailMessage);
  }
}
