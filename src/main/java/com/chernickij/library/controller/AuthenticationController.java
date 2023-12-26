package com.chernickij.library.controller;

import com.chernickij.library.config.security.JwtProvider;
import com.chernickij.library.dto.UserDto;
import com.chernickij.library.service.UserService;
import com.chernickij.library.dto.LoginDto;
import com.chernickij.library.dto.RegistrationDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        log.debug("Executing the login method for user with email: %s".formatted(loginDto.email()));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password())
        );

        String token = jwtProvider.buildToken(loginDto.email());
        response.addHeader(HttpHeaders.AUTHORIZATION, token);
        return token;

    }

    @PostMapping(value = "/register")
    public UserDto register(@RequestBody RegistrationDto registrationDto) {
        log.debug("Executing the registration method for user with email: %s".formatted(registrationDto.getEmail()));

        return userService.register(registrationDto);
    }
}
