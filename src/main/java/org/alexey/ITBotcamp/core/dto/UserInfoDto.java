package org.alexey.ITBotcamp.core.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.alexey.ITBotcamp.core.entity.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class UserInfoDto {

    private String fio;

    private String email;

    private UserRole role;
}
