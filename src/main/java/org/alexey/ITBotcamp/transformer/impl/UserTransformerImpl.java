package org.alexey.ITBotcamp.transformer.impl;

import org.alexey.ITBotcamp.core.dto.UserCreationDto;
import org.alexey.ITBotcamp.core.dto.UserInfoDto;
import org.alexey.ITBotcamp.core.entity.User;
import org.alexey.ITBotcamp.transformer.UserTransformer;
import org.springframework.stereotype.Component;

@Component
public class UserTransformerImpl implements UserTransformer {

    @Override
    public User transformEntityFromCreateDto(UserCreationDto userCreationDto) {
        return new User().setFirstName(userCreationDto.getFirstName())
                .setLastName(userCreationDto.getLastName())
                .setMiddleName(userCreationDto.getMiddleName())
                .setEmail(userCreationDto.getEmail())
                .setRole(userCreationDto.getRole());
    }

    @Override
    public UserInfoDto transformInfoDtoFromEntity(User user) {
        return new UserInfoDto().setFio(
                        user.getLastName() + " " + user.getFirstName() + " " + user.getMiddleName()
                )
                .setEmail(user.getEmail())
                .setRole(user.getRole());
    }
}
