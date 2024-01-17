package org.alexey.ITBotcamp.transformer.impl;

import org.alexey.ITBotcamp.core.dto.UserCreationDto;
import org.alexey.ITBotcamp.core.dto.UserInfoDto;
import org.alexey.ITBotcamp.core.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.alexey.ITBotcamp.core.entity.UserRole.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserTransformerImpTest {

    @InjectMocks
    private UserTransformerImpl userTransformer;

    @Test
    void testTransformEntityFromCreateDto() {

        UserCreationDto userCreationDto = new UserCreationDto()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setMiddleName("Ivanovich")
                .setEmail("IvanovII.@gmail.com")
                .setRole(USER);
        User expectedUser = new User()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setMiddleName("Ivanovich")
                .setEmail("IvanovII.@gmail.com")
                .setRole(USER);

        User resultUser = userTransformer.transformEntityFromCreateDto(userCreationDto);

        assertNotNull(resultUser);
        assertEquals(resultUser.getFirstName(), expectedUser.getFirstName());
        assertEquals(resultUser.getLastName(), expectedUser.getLastName());
        assertEquals(resultUser.getMiddleName(), expectedUser.getMiddleName());
        assertEquals(resultUser.getEmail(), expectedUser.getEmail());
        assertEquals(resultUser.getRole(), expectedUser.getRole());
    }

    @Test
    void testTransformInfoDtoFromEntity() {
        User user = new User()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setMiddleName("Ivanovich")
                .setEmail("IvanovII.@gmail.com")
                .setRole(USER);
        UserInfoDto expectedInfoDto = new UserInfoDto()
                .setFio("Ivanov Ivan Ivanovich")
                .setEmail("IvanovII.@gmail.com")
                .setRole(USER);

        UserInfoDto resultInfoDto = userTransformer.transformInfoDtoFromEntity(user);

        assertNotNull(resultInfoDto);
        assertEquals(resultInfoDto, expectedInfoDto);
    }
}
