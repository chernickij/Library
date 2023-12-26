package com.chernickij.library.service;

import com.chernickij.library.dto.PrivilegeDto;
import com.chernickij.library.dto.SavePrivilegeDto;

import java.util.List;

public interface PrivilegeService {
    List<PrivilegeDto> getAll(Integer offset, Integer limit);

    PrivilegeDto getById(long id);

    PrivilegeDto save(SavePrivilegeDto privilegeDto);

    PrivilegeDto update(long id, SavePrivilegeDto privilegeDto);

    void delete(long id);
}
