package mx.infotec.dads.pruebas.mail.service;

import javax.mail.MessagingException;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String message);

    void sendMessageWithAttachment(String to, String subject, String message, String pathToAttachment)
            throws MessagingException;
    
    void sendTestMessage(String to);
}