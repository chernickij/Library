package com.chernickij.library.controller;

import com.chernickij.library.dto.SaveAuthorDto;
import com.chernickij.library.service.AuthorService;
import com.chernickij.library.dto.AuthorDto;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<AuthorDto> authors = authorService.getAll(offset, limit);
        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("{/id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(authorService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody SaveAuthorDto authorDto) {
        return new ResponseEntity<>(authorService.save(authorDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable("id") Long id, @RequestBody SaveAuthorDto authorDto) {
        return new ResponseEntity<>(authorService.update(id, authorDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        authorService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
