package com.chernickij.library.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/auth/login", "/auth/register").permitAll()
                                .requestMatchers("/users/**").hasAnyAuthority("MANAGE_USER", "GET_USER")
                                .requestMatchers(GET, "/books/**").permitAll()
                                .requestMatchers(POST, "/books/**").hasAuthority("MANAGE_BOOK")
                                .requestMatchers(PUT, "/books/**").hasAuthority("MANAGE_BOOK")
                                .requestMatchers(DELETE, "/books/**").hasAuthority("MANAGE_BOOK")
                                .requestMatchers(GET, "/authors/**").hasAuthority("GET_AUTHOR")
                                .requestMatchers(POST, "/authors/**").hasAuthority("MANAGE_AUTHOR")
                                .requestMatchers(PUT, "/authors/**").hasAuthority("MANAGE_AUTHOR")
                                .requestMatchers(DELETE, "/authors/**").hasAuthority("MANAGE_AUTHOR")
                                .requestMatchers(GET, "/genres/**").hasAuthority("GET_GENRE")
                                .requestMatchers(POST, "/genres/**").hasAuthority("MANAGE_GENRE")
                                .requestMatchers(PUT, "/genres/**").hasAuthority("MANAGE_GENRE")
                                .requestMatchers(DELETE, "/genres/**").hasAuthority("MANAGE_GENRE")
//                                .requestMatchers(PUT, "/users/**").permitAll()

                                .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
