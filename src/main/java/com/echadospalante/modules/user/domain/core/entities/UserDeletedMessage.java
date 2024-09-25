package com.echadospalante.modules.user.domain.core.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDeletedMessage {

    private String reason;

    private String id;
    private String picture;
    private String email;
    private String firstName;
    private String lastName;
    private boolean active;
    private boolean verified;
    private boolean onboardingCompleted;

}
