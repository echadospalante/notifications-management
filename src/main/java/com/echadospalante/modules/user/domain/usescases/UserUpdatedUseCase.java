package com.echadospalante.modules.user.domain.usescases;

import com.echadospalante.modules.user.domain.core.entities.user.UserUpdatedMessage;
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
public class UserUpdatedUseCase {

    private final EmailSender emailSender;

    public void sendUserUpdatedMessage(UserUpdatedMessage message) {
        try {
            String subject = "¡Qué bien! Tu información ha sido actualizada";

            Resource resource = new ClassPathResource("templates/user_updated_message.html");
            String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            String processedHtml = htmlContent.replace("{{first_name}}", message.getFirstName());
//            processedHtml = processedHtml.replace("{{updated_at}}", message.getUpdatedAt().toString());

            this.emailSender.sendSimpleMessage(message.getEmail(), subject, processedHtml);
        } catch (IOException e) {
            throw new RuntimeException("Error al enviar el mensaje de actualización de información: ", e);
        }
    }
}
