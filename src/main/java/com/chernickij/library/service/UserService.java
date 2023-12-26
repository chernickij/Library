package com.chernickij.library.service;

import com.chernickij.library.dto.RegistrationDto;
import com.chernickij.library.dto.SaveUserDto;
import com.chernickij.library.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll(Integer offset, Integer limit);

    UserDto getById(long id);

    UserDto save(SaveUserDto entityDto);

    UserDto update(long id, SaveUserDto entityDto);

    void delete(long id);

    UserDto register(RegistrationDto registrationDto);
}
