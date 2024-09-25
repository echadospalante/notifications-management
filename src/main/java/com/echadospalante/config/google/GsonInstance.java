package com.echadospalante.config.google;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonInstance {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setLenient() // Permite un análisis más flexible
                .serializeNulls() // Incluye valores null en la serialización
                .setPrettyPrinting() // Salida JSON con formato legible
                .disableHtmlEscaping()
                .create();
    }
}