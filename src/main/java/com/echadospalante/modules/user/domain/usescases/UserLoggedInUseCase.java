package com.echadospalante.modules.user.domain.usescases;

import com.echadospalante.modules.user.domain.core.entities.user.UserLoggedInMessage;
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
public class UserLoggedInUseCase {

    private final EmailSender emailSender;

    public void sendUserLoggedInMessage(UserLoggedInMessage message) {
        try {
            String subject = "Nuevo inicio de sesion en tu cuenta de EchadosPa'lante";

            Resource resource = new ClassPathResource("templates/user_logged_message.html");
            String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            String processedHtml = htmlContent.replace("{{first_name}}", message.getFirstName());
//            processedHtml = processedHtml.replace("{{logged_in_date}}", message.getCreatedAt().toString());

            this.emailSender.sendSimpleMessage(message.getEmail(), subject, processedHtml);
        } catch (IOException e) {
            throw new RuntimeException("Error al enviar el mensaje de inicio de sesión: ", e);
        }
    }
}
