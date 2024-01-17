package org.alexey.ITBotcamp.service;

import org.alexey.ITBotcamp.core.dto.UserCreationDto;
import org.alexey.ITBotcamp.core.dto.UserInfoDto;
import org.alexey.ITBotcamp.core.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createUser(UserCreationDto userCreationDto);

    Page<UserInfoDto> getAllUsers(Pageable pageable);
}
