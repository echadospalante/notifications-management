package com.echadospalante.modules.user.domain.core.entities.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVerifiedMessage {

    private String id;
    private String picture;
    private String email;
    private String firstName;
    private String lastName;
    private boolean active;
    private boolean verified;
    private boolean onboardingCompleted;
}
