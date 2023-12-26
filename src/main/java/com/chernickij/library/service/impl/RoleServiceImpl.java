package com.chernickij.library.service.impl;

import com.chernickij.library.dto.SaveRoleDto;
import com.chernickij.library.exception.ResourceAlreadyExist;
import com.chernickij.library.exception.ResourceNotFoundException;
import com.chernickij.library.model.Role;
import com.chernickij.library.repository.PrivilegeRepository;
import com.chernickij.library.repository.RoleRepository;
import com.chernickij.library.service.RoleService;
import com.chernickij.library.dto.RoleDto;
import com.chernickij.library.mapper.PrivilegeMapper;
import com.chernickij.library.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.text.MessageFormat.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final RoleMapper roleMapper;
    private final PrivilegeMapper privilegeMapper;

    @Override
    @Transactional
    public List<RoleDto> getAll(Integer offset, Integer limit) {
        log.debug("Executing the roleService.getAll method with offset = %d and limit = %d".formatted(offset, limit));

        Page<Role> pagedResult = roleRepository.findAll(PageRequest.of(offset, limit));

        return pagedResult.hasContent()
                ? pagedResult.getContent().stream().map(roleMapper::mapToRoleDto).toList()
                : new ArrayList<>();
    }

    @Override
    @Transactional
    public RoleDto getById(long id) {
        log.debug("Executing the roleService.getById method with id {}", id);

        return roleRepository.findById(id)
                .map(roleMapper::mapToRoleDto)
                .orElseThrow(() -> new ResourceNotFoundException(format("Role with id {0} not found ", id)));
    }

    @Override
    @Transactional
    public RoleDto save(SaveRoleDto roleDto) {
        log.debug("Executing the roleService.save method for role with name: %s".formatted(roleDto.getName()));

        roleRepository.findByName(roleDto.getName()).ifPresent(privilege -> {
            throw new ResourceAlreadyExist(MessageFormat.format("Role with name {0} already exist", roleDto.getName()));
        });

        roleDto.getPrivileges().forEach(privilegeDto -> privilegeRepository.findByName(privilegeDto.getName())
                .orElseThrow(() -> new ResourceNotFoundException(format("Privilege with name {0} not found ", privilegeDto.getName()))));

        return roleMapper.mapToRoleDto(roleRepository.save(Role.builder()
                .name(roleDto.getName())
                .created(new Date())
                .privileges(roleDto.getPrivileges().stream().map(privilegeMapper::mapToPrivilege).toList())
                .build()));
    }

    @Override
    @Transactional
    public RoleDto update(long id, SaveRoleDto roleDto) {
        log.debug("Executing the roleService.save method for role with id: %s".formatted(id));

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Role with id {0} not found ", id)));

        roleDto.getPrivileges().forEach(privilegeDto -> privilegeRepository.findByName(privilegeDto.getName())
                .orElseThrow(() -> new ResourceNotFoundException(format("Privilege with name {0} not found ", privilegeDto.getName()))));

        role.setName(roleDto.getName());
        role.setPrivileges(roleDto.getPrivileges().stream().map(privilegeMapper::mapToPrivilege).toList());
        role.setUpdated(new Date());

        return roleMapper.mapToRoleDto(roleRepository.save(role));
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.debug("Executing the roleService.delete method for role with id: %s".formatted(id));

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Role with id {0} not found ", id)));
        roleRepository.delete(role);
    }
}
