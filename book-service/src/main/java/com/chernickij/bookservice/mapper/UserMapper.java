package com.chernickij.bookservice.mapper;

import com.chernickij.bookservice.dto.RegistrationDto;
import com.chernickij.bookservice.model.User;
import com.chernickij.bookservice.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User mapToUser(UserDto userDto);
    User mapRegistrationDto(RegistrationDto registrationDto);
    UserDto mapToUserDto(User user);
}
