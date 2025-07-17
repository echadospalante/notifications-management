package com.echadospalante.modules.user.infraestructure.mailing;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ModifyMessageRequest;
import com.echadospalante.modules.user.domain.core.gateway.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import com.google.api.services.gmail.model.Message;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.google.api.client.util.Base64;

@Service
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    public static final String USERS_TAG = "EchadosPalante - Usuarios";
    private final Gmail gmail;

    @Override
    public void sendSimpleMessage(String to, String subject, String body) throws IOException {
        String user = "me";
        String userId = "me";

        try {
            MimeMessage email = createEmail(to, user, subject, body);
            sendMessage(userId, email);
        } catch (MessagingException | jakarta.mail.MessagingException e) {
            e.printStackTrace(System.out);
        }
    }

    private MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException, jakarta.mail.MessagingException {
        Properties props = new Properties();

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(from));
        email.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);

        email.setContent(bodyText, "text/html");
        return email;
    }

    private void sendMessage(String userId, MimeMessage email) throws MessagingException, IOException, jakarta.mail.MessagingException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        Message sentMessage = gmail.users().messages().send(userId, message)
                .execute();
        String labelId = this.getUsersLabel(userId, USERS_TAG);
        ModifyMessageRequest mods = new ModifyMessageRequest().setAddLabelIds(Collections.singletonList(labelId));
        gmail.users().messages().modify(userId, sentMessage.getId(), mods).execute();
    }

    private String getUsersLabel(String userId, String labelName) throws IOException {
        ListLabelsResponse listResponse = gmail.users().labels().list(userId).execute();
        List<Label> labels = listResponse.getLabels();

        // Check if the label already exists
        for (Label label : labels) {
            if (label.getName().equalsIgnoreCase(labelName)) {
                return label.getId();
            }
        }
        // If not, create a new label
        Label newLabel = new Label().setName(labelName).setLabelListVisibility("labelShow").setMessageListVisibility("show");
        Label createdLabel = gmail.users().labels().create(userId, newLabel).execute();
        return createdLabel.getId();
    }
}
