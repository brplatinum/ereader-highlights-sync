package org.brplatinum.model;

import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    public static void sendEmail(EmailServer server, String destinationEmail) {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        if (server.getEncryption() == Encryption.TLS){
            properties.put("mail.smtp.starttls.enable", "true");
        } else if (server.getEncryption() == Encryption.SSL){
            properties.put("mail.smtp.ssl.enable", "true");
        } else {
            properties.put("mail.smtp.ssl.enable", "false");
        }
        properties.put("mail.smtp.port", Integer.toString(server.getPort()));
        properties.put("mail.smtp.host", server.getHostname());

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(server.getUsername(), server.getPassword());
            }
        });

        Message message = prepareMessage(session, server.getUsername(), destinationEmail);

        try {
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static Message prepareMessage(Session session, String fromEmail, String destinationEmail){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinationEmail));
            message.setSubject("Your Highlights");
            message.setText("Your Highlights");
            return message;
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}
