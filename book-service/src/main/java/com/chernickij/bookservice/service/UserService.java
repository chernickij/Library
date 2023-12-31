package com.chernickij.bookservice.service;

import com.chernickij.bookservice.dto.RegistrationDto;
import com.chernickij.bookservice.dto.SaveUserDto;
import com.chernickij.bookservice.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll(Integer offset, Integer limit);

    UserDto getById(long id);

    UserDto save(SaveUserDto entityDto);

    UserDto update(long id, SaveUserDto entityDto);

    void delete(long id);

    UserDto register(RegistrationDto registrationDto);
}
