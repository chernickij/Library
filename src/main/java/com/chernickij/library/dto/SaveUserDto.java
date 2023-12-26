package com.chernickij.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserDto {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private RoleDto role;
}
