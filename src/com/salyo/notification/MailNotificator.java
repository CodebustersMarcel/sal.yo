package com.salyo.notification;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by eugen.rieb on 12.05.2016.
 */
public class MailNotificator {
    final private static String sendTo = "marcel.weissgerber@wolterskluwer.com";
    final static String username = "notificator111@gmail.com";
    final static String password = "WoltersKluwer";

    public static boolean SendEmail(NotificationMessage notificationMessage) {

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            message.setSubject("Notification from salyo");
            message.setText(notificationMessage.getShortMessage()
                    + "\n" + notificationMessage.getFullMessage()
                    + "\n http://localhost:9998/notifications/" + notificationMessage.getId());
            Transport.send(message);
            return true;
        } catch (MessagingException mex) {
            return false;
        }
    }
}
