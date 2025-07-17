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
public class UserRegisteredUseCase {

    private final EmailSender emailSender;

    public void sendUserRegisteredMessage(UserRegisteredMessage message) {
        try {
            String subject = "¡Qué maravilla! Tu registro está completado en EchadosPa'lante";

            Resource resource = new ClassPathResource("templates/user_registered_message.html");
            String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            String processedHtml = htmlContent.replace("{{first_name}}", message.getFirstName());

            this.emailSender.sendSimpleMessage(message.getEmail(), subject, processedHtml);
        } catch (IOException e) {
            throw new RuntimeException("Error al enviar el mensaje de registro completado: ", e);
        }
    }
}
