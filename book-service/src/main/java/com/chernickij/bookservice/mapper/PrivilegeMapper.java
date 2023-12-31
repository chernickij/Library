package com.chernickij.bookservice.mapper;

import com.chernickij.bookservice.model.Privilege;
import com.chernickij.bookservice.dto.PrivilegeDto;
import org.mapstruct.Mapper;

@Mapper
public interface PrivilegeMapper {

    Privilege mapToPrivilege(PrivilegeDto privilegeDto);

    PrivilegeDto mapToPrivilegeDto(Privilege privilege);
}
