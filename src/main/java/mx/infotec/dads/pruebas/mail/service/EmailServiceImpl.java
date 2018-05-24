package mx.infotec.dads.pruebas.mail.service;

import java.io.File;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import mx.infotec.dads.pruebas.mail.config.ApplicationProperties;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public void sendSimpleMessage(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(applicationProperties.getFrom());
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String message, String pathToAttachment)
            throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(applicationProperties.getFrom());
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(message);

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendTestMessage(String to) {
        Date date = new Date();

        String title = String.format(applicationProperties.getSubject(), applicationProperties.getHostname(),
                date);
        String message = String.format(applicationProperties.getMessage(), applicationProperties.getHostname(),
                date);

        sendSimpleMessage(to, title, message);
    }
}