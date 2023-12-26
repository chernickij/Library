package com.chernickij.library.mapper;

import com.chernickij.library.model.Role;
import com.chernickij.library.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    Role mapToRole(RoleDto roleDto);

    RoleDto mapToRoleDto(Role role);
}
