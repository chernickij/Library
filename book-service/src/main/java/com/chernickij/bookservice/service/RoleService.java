package com.chernickij.bookservice.service;

import com.chernickij.bookservice.dto.RoleDto;
import com.chernickij.bookservice.dto.SaveRoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAll(Integer offset, Integer limit);

    RoleDto getById(long id);

    RoleDto save(SaveRoleDto roleDto);

    RoleDto update(long id, SaveRoleDto roleDto);

    void delete(long id);
}
