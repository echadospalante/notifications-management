package com.echadospalante.modules.user.domain.usescases;

import com.echadospalante.modules.user.domain.core.entities.user.UserDeletedMessage;
import com.echadospalante.modules.user.domain.core.gateway.EmailSender;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Log
@Builder
@RequiredArgsConstructor
public class UserDeletedUseCase {

    private final EmailSender emailSender;

    public void sendUserDeletedMessage(UserDeletedMessage message) {
        try {
            String subject = "Tu cuenta ha sido eliminada, esperamos verte pronto";

            Resource resource = new ClassPathResource("templates/user_deleted_message.html");
            String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            String processedHtml = htmlContent.replace("{{firstName}}", message.getEmail());
            processedHtml = processedHtml.replace("{{reason}}", message.getReason());


            this.emailSender.sendSimpleMessage(message.getEmail(), subject, processedHtml);

        } catch (IOException e) {
            throw new RuntimeException("Error al enviar el mensaje: ", e);
        }
    }
}
