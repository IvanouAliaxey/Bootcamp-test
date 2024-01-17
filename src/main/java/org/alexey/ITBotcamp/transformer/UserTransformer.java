package org.alexey.ITBotcamp.transformer;

import org.alexey.ITBotcamp.core.dto.UserCreationDto;
import org.alexey.ITBotcamp.core.dto.UserInfoDto;
import org.alexey.ITBotcamp.core.entity.User;

public interface UserTransformer {

    User transformEntityFromCreateDto(UserCreationDto userCreationDto);

    UserInfoDto transformInfoDtoFromEntity(User user);
}
