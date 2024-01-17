package org.alexey.ITBotcamp.service.impl;

import org.alexey.ITBotcamp.core.dto.UserCreationDto;
import org.alexey.ITBotcamp.core.dto.UserInfoDto;
import org.alexey.ITBotcamp.core.entity.User;
import org.alexey.ITBotcamp.exception.ValidationException;
import org.alexey.ITBotcamp.repository.UserRepository;
import org.alexey.ITBotcamp.transformer.UserTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.alexey.ITBotcamp.core.entity.UserRole.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserTransformer userTransformer;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testCreateUser() {

        UserCreationDto userCreationDto = new UserCreationDto()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setMiddleName("Ivanovich")
                .setEmail("IvanovII.@gmail.com")
                .setRole(USER);
        User expectedResult = new User()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setMiddleName("Ivanovich")
                .setEmail("IvanovII.@gmail.com")
                .setRole(USER);

        when(userRepository.existsByEmail(userCreationDto.getEmail())).thenReturn(false);
        when(userTransformer.transformEntityFromCreateDto(userCreationDto)).thenReturn(expectedResult);
        when(userRepository.save(expectedResult)).thenReturn(expectedResult);

        User actualResult = userService.createUser(userCreationDto);

        assertNotNull(actualResult);
        assertEquals(actualResult.getEmail(), expectedResult.getEmail());
    }

    @Test
    void testCreateUserWithExistingEmail() {

        UserCreationDto userCreationDto = new UserCreationDto()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setMiddleName("Ivanovich")
                .setEmail("IvanovII.@gmail.com")
                .setRole(USER);

        when(userRepository.existsByEmail(userCreationDto.getEmail())).thenReturn(true);

        assertThrows(ValidationException.class, () -> userService.createUser(userCreationDto));
    }

    @Test
    void testGetAllUsers() {

        Pageable pageable = mock(Pageable.class);
        Page<User> entityPage = new PageImpl<>(List.of(new User()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setMiddleName("Ivanovich")
                .setEmail("IvanovII.@gmail.com")
                .setRole(USER)
        ));
        Page<UserInfoDto> page = new PageImpl<>(List.of(new UserInfoDto()
                .setFio("Ivanov Ivan Ivanovich")
                .setEmail("IvanovII.@gmail.com")
                .setRole(USER)
        ));

        when(userRepository.findAllByOrderByEmailAsc(pageable)).thenReturn(entityPage);
        when(userTransformer.transformInfoDtoFromEntity(any(User.class))).thenReturn(new UserInfoDto()
                .setFio("Ivanov Ivan Ivanovich")
                .setEmail("IvanovII.@gmail.com")
                .setRole(USER)
        );

        Page<UserInfoDto> resultPage = userService.getAllUsers(pageable);

        assertNotNull(resultPage);
        assertEquals(resultPage.getContent().get(0), page.getContent().get(0));
    }
}
