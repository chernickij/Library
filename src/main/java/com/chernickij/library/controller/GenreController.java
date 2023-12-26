package com.chernickij.library.controller;

import com.chernickij.library.dto.SaveGenreDto;
import com.chernickij.library.service.GenreService;
import com.chernickij.library.dto.GenreDto;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<GenreDto> genres = genreService.getAll(offset, limit);

        if (genres.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @GetMapping("{/id}")
    public ResponseEntity<GenreDto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(genreService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenreDto> create(@RequestBody SaveGenreDto genreDto) {
        return new ResponseEntity<>(genreService.save(genreDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDto> update(@PathVariable("id") Long id, @RequestBody SaveGenreDto genreDto) {
        return new ResponseEntity<>(genreService.update(id, genreDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        genreService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
