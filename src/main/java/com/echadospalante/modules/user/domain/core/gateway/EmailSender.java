package com.echadospalante.modules.user.domain.core.gateway;

import java.io.IOException;

public interface EmailSender {
    void sendSimpleMessage(String to, String subject, String body) throws IOException;
}
