package com.chernickij.library.controller;

import com.chernickij.library.dto.SaveUserDto;
import com.chernickij.library.dto.UserDto;
import com.chernickij.library.service.UserService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        List<UserDto> users = userService.getAll(offset, limit);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{/id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody SaveUserDto userDto) {
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") long id, @RequestBody SaveUserDto userDto) {
        return new ResponseEntity<>(userService.update(id, userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable("id") long id) {
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
