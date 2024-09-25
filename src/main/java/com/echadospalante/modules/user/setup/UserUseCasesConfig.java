package com.echadospalante.modules.user.setup;

import com.echadospalante.modules.user.domain.core.gateway.EmailSender;
import com.echadospalante.modules.user.domain.usescases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCasesConfig {

    @Bean
    public  UserCreatedUseCase userCreatedUseCase(EmailSender emailSender) {
        return UserCreatedUseCase.builder()
                .emailSender(emailSender)
                .build();
    }

    @Bean
    public  UserDeletedUseCase userDeletedMessage(EmailSender emailSender) {
        return UserDeletedUseCase.builder()
                .emailSender(emailSender)
                .build();
    }

    @Bean
    public  UserDisabledUseCase userDisabledMessage(EmailSender emailSender) {
        return UserDisabledUseCase.builder()
                .emailSender(emailSender)
                .build();
    }

    @Bean
    public  UserEnabledUseCase userEnabledMessage(EmailSender emailSender) {
        return UserEnabledUseCase.builder()
                .emailSender(emailSender)
                .build();
    }

    @Bean
    public  UserLoggedInUseCase userLoggedInUseCase(EmailSender emailSender) {
        return UserLoggedInUseCase.builder()
                .emailSender(emailSender)
                .build();
    }

    @Bean
    public  UserRegisteredUseCase userRegisteredMessage(EmailSender emailSender) {
        return UserRegisteredUseCase.builder()
                .emailSender(emailSender)
                .build();
    }

    @Bean
    public  UserVerifiedUseCase userVerifiedUseCase(EmailSender emailSender) {
        return UserVerifiedUseCase.builder()
                .emailSender(emailSender)
                .build();
    }

    @Bean
    public  UserUnverifiedUseCase userUnverifiedUseCase(EmailSender emailSender) {
        return UserUnverifiedUseCase.builder()
                .emailSender(emailSender)
                .build();
    }

    @Bean
    public  UserUpdatedUseCase userUpdatedUseCase(EmailSender emailSender) {
        return UserUpdatedUseCase.builder()
                .emailSender(emailSender)
                .build();
    }
}
