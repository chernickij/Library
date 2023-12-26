package com.chernickij.library.controller;

import com.chernickij.library.dto.SaveBookDto;
import com.chernickij.library.service.BookService;
import com.chernickij.library.dto.BookDto;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        List<BookDto> books = bookService.getAll(offset, limit);

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("{/id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody SaveBookDto bookDto) {
        return new ResponseEntity<>(bookService.save(bookDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable("id") Long id, @RequestBody SaveBookDto bookDto) {
        return new ResponseEntity<>(bookService.update(id, bookDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        bookService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
