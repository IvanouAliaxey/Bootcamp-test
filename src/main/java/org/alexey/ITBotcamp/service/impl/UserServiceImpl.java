package org.alexey.ITBotcamp.service.impl;

import org.alexey.ITBotcamp.core.dto.UserCreationDto;
import org.alexey.ITBotcamp.core.dto.UserInfoDto;
import org.alexey.ITBotcamp.core.entity.User;
import org.alexey.ITBotcamp.exception.ValidationException;
import org.alexey.ITBotcamp.repository.UserRepository;
import org.alexey.ITBotcamp.service.UserService;
import org.alexey.ITBotcamp.transformer.UserTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTransformer userTransformer;

    public UserServiceImpl(UserRepository userRepository, UserTransformer userTransformer) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
    }

    @Override
    public User createUser(UserCreationDto userCreationDto) {
        validateEmailNotExists(userCreationDto.getEmail());
        return userRepository.save(userTransformer.transformEntityFromCreateDto(userCreationDto));
    }

    @Override
    public Page<UserInfoDto> getAllUsers(Pageable pageable) {
        Page<User> entityPage = userRepository.findAllByOrderByEmailAsc(pageable);
        List<UserInfoDto> dtoList = entityPage.stream()
                .map(userTransformer::transformInfoDtoFromEntity)
                .toList();
        return new PageImpl<UserInfoDto>(dtoList, entityPage.getPageable(), entityPage.getTotalElements());
    }

    private void validateEmailNotExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ValidationException("The specified email address is already registered: " + email);
        }
    }
}
