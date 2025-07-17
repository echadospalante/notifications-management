package com.echadospalante.modules.user.infraestructure.listeners;

import com.echadospalante.config.google.GsonInstance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicationEventsListeners {

    private final GsonInstance gsonInstance;


}
