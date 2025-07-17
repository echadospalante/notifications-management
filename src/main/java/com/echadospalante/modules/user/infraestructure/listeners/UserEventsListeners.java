package com.echadospalante.modules.user.infraestructure.listeners;

import com.echadospalante.config.google.GsonInstance;
import com.echadospalante.modules.user.domain.core.entities.user.*;
import com.echadospalante.modules.user.domain.usescases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventsListeners {

    private final GsonInstance gsonInstance;

    private final UserCreatedUseCase userCreatedUseCase;
    private final UserDeletedUseCase userDeletedUseCase;
    private final UserEnabledUseCase userEnabledUseCase;
    private final UserDisabledUseCase userDisabledUseCase;
    private final UserLoggedInUseCase userLoggedInUseCase;
    private final UserRegisteredUseCase userRegisteredUseCase;
    private final UserVerifiedUseCase userVerifiedUseCase;
    private final UserUnverifiedUseCase userUnverifiedUseCase;
    private final UserUpdatedUseCase userUpdatedUseCase;

    @RabbitListener(queues = "users.created.queue")
    public void userCreatedListener(String msgString) {
        UserRegisteredMessage message = gsonInstance.gson().fromJson(msgString, UserRegisteredMessage.class);
        this.userCreatedUseCase.sendUserCreatedMessage(message);
    }

    @RabbitListener(queues = "users.registered.queue")
    public void userRegisteredListener(String msgString) {
        UserRegisteredMessage message = gsonInstance.gson().fromJson(msgString, UserRegisteredMessage.class);
        this.userRegisteredUseCase.sendUserRegisteredMessage(message);
    }

    @RabbitListener(queues = "users.logged.queue")
    public void userLoggedListener(String msgString) {
        System.out.println(msgString);
        UserLoggedInMessage message = gsonInstance.gson().fromJson(msgString, UserLoggedInMessage.class);
        this.userLoggedInUseCase.sendUserLoggedInMessage(message);
    }

    @RabbitListener(queues = "users.disabled.queue")
    public void userDisabledListener(String msgString) {
        UserDisabledMessage message = gsonInstance.gson().fromJson(msgString, UserDisabledMessage.class);
        this.userDisabledUseCase.sendUserDisabledMessage(message);
    }

    @RabbitListener(queues = "users.enabled.queue")
    public void userEnabledListener(String msgString) {
        UserEnabledMessage message = gsonInstance.gson().fromJson(msgString, UserEnabledMessage.class);
        this.userEnabledUseCase.sendUserEnabledMessage(message);
    }

    @RabbitListener(queues = "users.updated.queue")
    public void userUpdatedListener(String msgString) {
        UserUpdatedMessage message = gsonInstance.gson().fromJson(msgString, UserUpdatedMessage.class);
        this.userUpdatedUseCase.sendUserUpdatedMessage(message);
    }

    @RabbitListener(queues = "users.verified.queue")
    public void userVerifiedListener(String msgString) {
        UserVerifiedMessage message = gsonInstance.gson().fromJson(msgString, UserVerifiedMessage.class);
        this.userVerifiedUseCase.sendUserVerifiedMessage(message);
    }

    @RabbitListener(queues = "users.unverified.queue")
    public void userUnverifiedListener(String msgString) {
        UserUnverifiedMessage message = gsonInstance.gson().fromJson(msgString, UserUnverifiedMessage.class);
        this.userUnverifiedUseCase.sendUserUnverifiedMessage(message);
    }

    @RabbitListener(queues = "users.inactivated.queue")
    public void userInactivatedListener(String msgString) {
        UserDeletedMessage message = gsonInstance.gson().fromJson(msgString, UserDeletedMessage.class);
        this.userDeletedUseCase.sendUserDeletedMessage(message);
    }

    @RabbitListener(queues = "users.reactivated.queue")
    public void userReactivatedListener(String msgString) {
        UserDeletedMessage message = gsonInstance.gson().fromJson(msgString, UserDeletedMessage.class);
        this.userDeletedUseCase.sendUserDeletedMessage(message);
    }

}
