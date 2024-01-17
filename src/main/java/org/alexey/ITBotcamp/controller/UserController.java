package org.alexey.ITBotcamp.controller;

import org.alexey.ITBotcamp.core.dto.PageDto;
import org.alexey.ITBotcamp.core.dto.UserCreationDto;
import org.alexey.ITBotcamp.core.dto.UserInfoDto;
import org.alexey.ITBotcamp.service.UserService;
import org.alexey.ITBotcamp.transformer.PageTransformer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final PageTransformer pageTransformer;

    public UserController(UserService userService,
                          PageTransformer pageTransformer) {
        this.userService = userService;
        this.pageTransformer = pageTransformer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@Validated @RequestBody UserCreationDto userCreationDto) {
        userService.createUser(userCreationDto);
    }

    @GetMapping
    public PageDto<UserInfoDto> getAllUsers(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return pageTransformer.transformPageDtoFromPage(userService.getAllUsers(pageable));
    }
}
