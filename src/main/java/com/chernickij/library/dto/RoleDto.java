package com.chernickij.library.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private long id;
    private String name;
    private List<PrivilegeDto> privileges;
}
