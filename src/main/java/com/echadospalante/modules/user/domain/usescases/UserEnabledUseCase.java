package com.echadospalante.modules.user.domain.usescases;

import com.echadospalante.modules.user.domain.core.entities.*;
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
public class UserEnabledUseCase {

    private final EmailSender emailSender;

    public void sendUserEnabledMessage(UserEnabledMessage message) {
        try {
            String subject = "!Hola! - Bienvenido de nuevo a EchadosPa'lante";

            Resource resource = new ClassPathResource("templates/user_enabled_message.html");
            String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            String processedHtml = htmlContent.replace("{{first_name}}", message.getFirstName());
            processedHtml = processedHtml.replace("{{first_name}}", message.getFirstName());

            this.emailSender.sendSimpleMessage(message.getEmail(), subject, processedHtml);
        } catch (IOException e) {
            throw new RuntimeException("Error al enviar el mensaje de cuenta habilitada: ", e);
        }
    }
}
