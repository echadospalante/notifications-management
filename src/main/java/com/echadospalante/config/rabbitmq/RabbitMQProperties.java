package com.echadospalante.config.rabbitmq;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.rabbitmq.setup")
public class RabbitMQProperties {

    private String host;
    private int port;
    private String username;
    private String password;
    private String virtualHost;

    private Map<String, String> exchange;
    private Map<String, String> queues;
    private Map<String, ExchangeConfig> bindings;

    @Data
    public static class ExchangeConfig {
        private String queue;
        private String exchange;
        private String routingKey;

        // Getters and setters for queue, exchange, and routingKey
    }

    @Data
    public static class QueueConfig {
        private String name;
        private boolean durable;
        private boolean exclusive;
        private boolean autoDelete;
    }


    // Getters and setters for all fields
}
