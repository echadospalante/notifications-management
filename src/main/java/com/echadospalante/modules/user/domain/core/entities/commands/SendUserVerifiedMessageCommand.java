package com.echadospalante.modules.user.domain.core.entities.commands;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class SendUserVerifiedMessageCommand {

    private String nombre;
    private String cedula;
    private String telefono;

}
