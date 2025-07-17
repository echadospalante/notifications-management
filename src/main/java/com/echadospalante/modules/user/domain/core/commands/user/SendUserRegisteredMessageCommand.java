package com.echadospalante.modules.user.domain.core.commands.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class SendUserRegisteredMessageCommand {

    private String nombre;
    private String cedula;
    private String telefono;

}
