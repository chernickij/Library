package com.chernickij.bookservice.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtProvider jwtProvider;

    public LoginFilter(JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        response.addHeader(HttpHeaders.AUTHORIZATION, jwtProvider.buildToken(authResult.getName()));
    }
}
