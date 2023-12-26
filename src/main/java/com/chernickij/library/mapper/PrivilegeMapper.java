package com.chernickij.library.mapper;

import com.chernickij.library.model.Privilege;
import com.chernickij.library.dto.PrivilegeDto;
import org.mapstruct.Mapper;

@Mapper
public interface PrivilegeMapper {

    Privilege mapToPrivilege(PrivilegeDto privilegeDto);

    PrivilegeDto mapToPrivilegeDto(Privilege privilege);
}
