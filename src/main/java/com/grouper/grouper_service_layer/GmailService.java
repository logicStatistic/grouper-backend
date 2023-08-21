package com.grouper.grouper_service_layer;


import com.google.api.services.gmail.Gmail;
import com.grouper.grouper_exception_control.EmailFailedToSendException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

@Service
public class GmailService {

    private final Gmail gmail;

    @Autowired
    public GmailService(Gmail gmail) {
        this.gmail = gmail;
    }

    public void sendEmail(String toAddress, String subject, String content) throws Exception {

        Properties prop = new Properties();
        Session session = Session.getInstance(prop, null);
        MimeMessage email = new MimeMessage(session);

        try{
           email.setFrom(new InternetAddress("kingsleyafamefula@gmail.com"));
           email.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
           email.setSubject(subject);
           email.setText(content);

            ByteArrayOutputStream byteArrayOutputStream =
                    new ByteArrayOutputStream();
            email.writeTo(byteArrayOutputStream);

            byte[] rawMessage = byteArrayOutputStream.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessage);

            com.google.api.services.gmail.model.Message message =
                    new com.google.api.services.gmail.model.Message();
            message.setRaw(encodedEmail);

            message = gmail.users().messages().send("me",message).execute();

        } catch (Exception e){
            throw new EmailFailedToSendException();

        }
    }

}
