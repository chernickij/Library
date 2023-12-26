package com.chernickij.library.service.impl;

import com.chernickij.library.dto.RegistrationDto;
import com.chernickij.library.dto.SaveUserDto;
import com.chernickij.library.dto.UserDto;
import com.chernickij.library.exception.ResourceAlreadyExist;
import com.chernickij.library.exception.ResourceNotFoundException;
import com.chernickij.library.model.*;
import com.chernickij.library.repository.RoleRepository;
import com.chernickij.library.repository.UserRepository;
import com.chernickij.library.service.UserService;
import com.chernickij.library.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAll(Integer offset, Integer limit) {
        log.debug("Executing the userService.getAll method with offset = %d and limit = %d".formatted(offset, limit));

        Page<User> pagedResult = userRepository.findAll(PageRequest.of(offset, limit));

        return pagedResult.hasContent()
                ? pagedResult.getContent().stream().map(userMapper::mapToUserDto).toList()
                : new ArrayList<>();
    }

    @Override
    public UserDto getById(long id) {
        log.debug("Executing the userService.getById method with id {}", id);

        return userRepository.findById(id)
                .map(userMapper::mapToUserDto)
                .orElseThrow(() -> new ResourceNotFoundException(format("User with id {0} not found ", id)));

    }

    @Override
    @Transactional
    public UserDto register(RegistrationDto registrationDto) {
        log.debug("Executing the userService.registration method for user with email: %s".formatted(registrationDto.getEmail()));

        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new ResourceAlreadyExist(String.format("User with the email address %s already exists", registrationDto.getEmail()));
        }

        User user = userMapper.mapRegistrationDto(registrationDto);
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        // TODO: Need to improve basic user role logic
        long basicUserRoleId = 2;
        Role role = getRole(basicUserRoleId);
        user.setRole(role);
        return userMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto save(SaveUserDto userDto) {
        log.debug("Executing the userService.save method for user with email: %s".formatted(userDto.getEmail()));

        userRepository.findByEmail(userDto.getEmail()).ifPresent(user -> {
            throw new ResourceAlreadyExist(MessageFormat.format("User with email {0} already exist", userDto.getEmail()));
        });

        Role role = getRole(userDto.getRole().getId());

        return userMapper.mapToUserDto(userRepository.save(User.builder()
                .password(passwordEncoder.encode(userDto.getPassword()))
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .role(role)
                .build()));
    }

    @Override
    @Transactional
    public UserDto update(long id, SaveUserDto userDto) {
        log.debug("Executing the userService.update method for user with id: %s".formatted(id));

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("User with id {0} not found ", id)));

        Role role = getRole(userDto.getRole().getId());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setRole(role);

        return userMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.debug("Executing the userService.delete method for user with id: %s".formatted(id));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("User with id {0} not found ", id)));
        userRepository.delete(user);
    }


    private Role getRole(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        roleOptional.orElseThrow(() -> new ResourceNotFoundException(format("Role with id {0} not found ", id)));
        return roleOptional.get();
    }
}
