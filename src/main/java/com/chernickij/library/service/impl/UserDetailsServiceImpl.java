package com.chernickij.library.service.impl;

import com.chernickij.library.model.Privilege;
import com.chernickij.library.model.Role;
import com.chernickij.library.model.User;
import com.chernickij.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.debug("Executing method userDetailsService.loadUserByUsername with username {}", username);

        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new BadCredentialsException(String.format("User with mail %s not found", username));
        }

        return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
                user.get().getPassword(),
                getAuthorities(user.get().getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Role role) {
        List<String> privileges = new ArrayList<>();
        privileges.add(role.getName());
        role.getPrivileges().stream().map(Privilege::getName).forEach(privileges::add);
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        return privileges.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
