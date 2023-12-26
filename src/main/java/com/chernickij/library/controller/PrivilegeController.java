package com.chernickij.library.controller;

import com.chernickij.library.dto.SavePrivilegeDto;
import com.chernickij.library.service.PrivilegeService;
import com.chernickij.library.dto.PrivilegeDto;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/privileges")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @GetMapping
    public ResponseEntity<List<PrivilegeDto>> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        List<PrivilegeDto> privileges = privilegeService.getAll(offset, limit);

        if (privileges.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(privileges, HttpStatus.OK);
    }

    @GetMapping("{/id}")
    public ResponseEntity<PrivilegeDto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(privilegeService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PrivilegeDto> create(@RequestBody SavePrivilegeDto privilegeDto) {
        return new ResponseEntity<>(privilegeService.save(privilegeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrivilegeDto> update(@PathVariable("id") Long id, @RequestBody SavePrivilegeDto privilegeDto) {
        return new ResponseEntity<>(privilegeService.update(id, privilegeDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        privilegeService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
