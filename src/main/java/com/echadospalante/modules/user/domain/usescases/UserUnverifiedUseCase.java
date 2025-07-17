package com.echadospalante.modules.user.domain.usescases;

import com.echadospalante.modules.user.domain.core.entities.user.UserUnverifiedMessage;
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
public class UserUnverifiedUseCase {

    private final EmailSender emailSender;

    public void sendUserUnverifiedMessage(UserUnverifiedMessage message) {
        try {
            String subject = "¡Pilas! Tu cuenta ya no está verificada";

            Resource resource = new ClassPathResource("templates/user_unverified_message.html");
            String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            String processedHtml = htmlContent.replace("{{first_name}}", message.getFirstName());
            processedHtml = processedHtml.replace("{{reason}}", "RAZON DE PRUEBA");
//            processedHtml = processedHtml.replace("{{from_date}}", message.getUpdatedAt().toString());

            this.emailSender.sendSimpleMessage(message.getEmail(), subject, processedHtml);
        } catch (IOException e) {
            throw new RuntimeException("Error al enviar el mensaje de cuenta no verificada: ", e);
        }
    }
}
