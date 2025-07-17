package com.echadospalante.modules.user.domain.usescases;

import com.echadospalante.modules.user.domain.core.entities.user.UserVerifiedMessage;
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
public class UserVerifiedUseCase {

    private final EmailSender emailSender;

    public void sendUserVerifiedMessage(UserVerifiedMessage message) {
        try {
            String subject = "¡Qué bacanería! Tu cuenta ha sido verificada";

            Resource resource = new ClassPathResource("templates/user_verified_message.html");
            String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            String processedHtml = htmlContent.replace("{{title}}", message.getFirstName());
//            processedHtml = processedHtml.replace("{{verified_at}}", message.getUpdatedAt().toString());

            this.emailSender.sendSimpleMessage(message.getEmail(), subject, processedHtml);
        } catch (IOException e) {
            throw new RuntimeException("Error al enviar el mensaje de cuenta verificada: ", e);
        }
    }
}
