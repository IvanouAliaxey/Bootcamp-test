package org.alexey.ITBotcamp.transformer.impl;

import org.alexey.ITBotcamp.core.dto.PageDto;
import org.alexey.ITBotcamp.core.dto.UserInfoDto;
import org.alexey.ITBotcamp.transformer.PageTransformer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageTransformerImpl implements PageTransformer {

    @Override
    public PageDto<UserInfoDto> transformPageDtoFromPage(Page<UserInfoDto> page) {
        return new PageDto<UserInfoDto>().setNumber(page.getNumber())
                .setSize(page.getSize())
                .setTotalPages(page.getTotalPages())
                .setTotalElements(page.getTotalElements())
                .setFirst(page.isFirst())
                .setNumberOfElements(page.getNumberOfElements())
                .setLast(page.isLast())
                .setContent(page.getContent());
    }

}
