package com.chernickij.library.mapper;

import com.chernickij.library.dto.RegistrationDto;
import com.chernickij.library.model.User;
import com.chernickij.library.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User mapToUser(UserDto userDto);
    User mapRegistrationDto(RegistrationDto registrationDto);
    UserDto mapToUserDto(User user);
}
