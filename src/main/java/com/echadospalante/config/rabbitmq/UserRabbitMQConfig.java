package com.echadospalante.config.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@RequiredArgsConstructor
public class UserRabbitMQConfig {

    @Bean
    public Exchange userExchange() {
        return ExchangeBuilder.topicExchange("users.exchange")
                .durable(true)
                .build();
    }

    @Bean
    public Queue userRegisteredQueue() {
        return new Queue("users.registered.queue", true);
    }

    @Bean
    public Queue userLoggedQueue() {
        return new Queue("users.logged.queue", true);
    }

    @Bean
    public Queue userCreatedQueue() {
        return new Queue("users.created.queue", true);
    }

    @Bean
    public Queue userDeletedQueue() {
        return new Queue("users.deleted.queue", true);
    }

    @Bean
    public Queue userDisabledQueue() {
        return new Queue("users.disabled.queue", true);
    }

    @Bean
    public Queue userEnabledQueue() {
        return new Queue("users.enabled.queue", true);
    }

    @Bean
    public Queue userUpdatedQueue() {
        return new Queue("users.updated.queue", true);
    }

    @Bean
    public Queue userVerifiedQueue() {
        return new Queue("users.verified.queue", true);
    }

    @Bean
    public Queue userUnverifieQueue() {
        return new Queue("users.unverified.queue", true);
    }

    @Bean
    public List<BindingBuilder.GenericArgumentsConfigurer> userBindings() {
        return Stream.of(
                        userRegisteredQueue(),
                        userCreatedQueue(),
                        userDeletedQueue(),
                        userDisabledQueue(),
                        userEnabledQueue(),
                        userLoggedQueue(),
                        userUpdatedQueue(),
                        userVerifiedQueue(),
                        userUnverifieQueue()
                ).map(queue -> {
                    int lastDotIndex = queue.getName().lastIndexOf(".");
                    String rk = queue.getName().concat(".rk");
                    if (lastDotIndex != -1) {
                        rk = queue.getName().substring(0, lastDotIndex).concat(".rk");
                    }
                    System.out.println(rk);
                    return BindingBuilder.bind(queue)
                            .to(userExchange())
                            .with(rk);
                })
                .collect(Collectors.toList());
    }

}