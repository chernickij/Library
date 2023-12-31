package com.chernickij.bookservice.controller;

import com.chernickij.bookservice.config.security.JwtProvider;
import com.chernickij.bookservice.dto.UserDto;
import com.chernickij.bookservice.service.UserService;
import com.chernickij.bookservice.dto.LoginDto;
import com.chernickij.bookservice.dto.RegistrationDto;
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
