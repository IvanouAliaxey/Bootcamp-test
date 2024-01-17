package org.alexey.ITBotcamp.transformer.impl;

import org.alexey.ITBotcamp.core.dto.PageDto;
import org.alexey.ITBotcamp.core.dto.UserInfoDto;
import org.alexey.ITBotcamp.service.impl.UserServiceImpl;
import org.alexey.ITBotcamp.transformer.PageTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.alexey.ITBotcamp.core.entity.UserRole.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PageTransformerImplTest {

    @InjectMocks
    private PageTransformerImpl pageTransformer;

    @Test
    void testTransformPageDtoFromPage() {

        Page<UserInfoDto> page = new PageImpl<>(List.of(new UserInfoDto()
                .setFio("Ivanov Ivan Ivanovich")
                .setEmail("IvanovII.@gmail.com")
                .setRole(USER)
        ), PageRequest.of(0, 10), 1);

        PageDto<UserInfoDto> pageDto = pageTransformer.transformPageDtoFromPage(page);

        assertNotNull(pageDto);
        assertEquals(pageDto.getContent().get(0), page.getContent().get(0));
    }
}
