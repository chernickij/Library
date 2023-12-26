package com.chernickij.library.service;

import com.chernickij.library.dto.RoleDto;
import com.chernickij.library.dto.SaveRoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAll(Integer offset, Integer limit);

    RoleDto getById(long id);

    RoleDto save(SaveRoleDto roleDto);

    RoleDto update(long id, SaveRoleDto roleDto);

    void delete(long id);
}
