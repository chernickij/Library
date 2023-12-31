package com.chernickij.bookservice.service;

import com.chernickij.bookservice.dto.PrivilegeDto;
import com.chernickij.bookservice.dto.SavePrivilegeDto;

import java.util.List;

public interface PrivilegeService {
    List<PrivilegeDto> getAll(Integer offset, Integer limit);

    PrivilegeDto getById(long id);

    PrivilegeDto save(SavePrivilegeDto privilegeDto);

    PrivilegeDto update(long id, SavePrivilegeDto privilegeDto);

    void delete(long id);
}
