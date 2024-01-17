package org.alexey.ITBotcamp.transformer;

import org.alexey.ITBotcamp.core.dto.PageDto;
import org.alexey.ITBotcamp.core.dto.UserInfoDto;
import org.springframework.data.domain.Page;

public interface PageTransformer {

    PageDto<UserInfoDto> transformPageDtoFromPage(Page<UserInfoDto> page);
}
