package com.chernickij.library.controller;

import com.chernickij.library.dto.SaveRoleDto;
import com.chernickij.library.service.RoleService;
import com.chernickij.library.dto.RoleDto;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        List<RoleDto> roles = roleService.getAll(offset, limit);

        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("{/id}")
    public ResponseEntity<RoleDto> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(roleService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDto> create(@RequestBody SaveRoleDto roleDto) {
        return new ResponseEntity<>(roleService.save(roleDto), HttpStatus.CREATED);
    }

    @PutMapping("{/id}")
    public ResponseEntity<RoleDto> update(@PathVariable("id") Long id, @RequestBody SaveRoleDto roleDto) {
        return new ResponseEntity<>(roleService.update(id, roleDto), HttpStatus.OK);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<RoleDto> delete(@PathVariable("id") Long id) {
        roleService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
