package com.chernickij.bookservice.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
