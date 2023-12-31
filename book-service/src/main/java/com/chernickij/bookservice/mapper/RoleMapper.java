package com.chernickij.bookservice.mapper;

import com.chernickij.bookservice.model.Role;
import com.chernickij.bookservice.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    Role mapToRole(RoleDto roleDto);

    RoleDto mapToRoleDto(Role role);
}
