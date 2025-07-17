package com.echadospalante.modules.user.domain.usescases;

import com.echadospalante.modules.user.domain.core.entities.user.UserRegisteredMessage;
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
public class UserCreatedUseCase {

    private final EmailSender emailSender;

    public void sendUserCreatedMessage(UserRegisteredMessage message) {
        try {
            String subject = "Bienvenido a EchadosPa'lante, estamos felices de tenerte con nosotros!";

            Resource resource = new ClassPathResource("templates/user_created_message.html");
            String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            String processedHtml = htmlContent.replace("{{first_name}}", message.getFirstName());

            this.emailSender.sendSimpleMessage(message.getEmail(), subject, processedHtml);
        } catch (IOException e) {
            throw new RuntimeException("Ocurrió un error al enviar el correo de bienvenida: " + e.getMessage());
        }
    }
}
