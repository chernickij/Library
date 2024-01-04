package com.chernickij.libraryservice.controller;

import com.chernickij.libraryservice.dto.BookInfoDto;
import com.chernickij.libraryservice.service.BookInfoService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookInfoController {

    @Autowired
    private BookInfoService bookInfoService;

    @GetMapping
    public ResponseEntity<List<BookInfoDto>> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        List<BookInfoDto> books = bookInfoService.getAll(offset, limit);

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookInfoDto> getByBookId(@PathVariable("id") Long bookId) {
        return new ResponseEntity<>(bookInfoService.getByBookId(bookId), HttpStatus.OK);
    }

    @PutMapping("/take/{id}")
    public ResponseEntity<BookInfoDto> takeBook(@PathVariable("id") Long bookId, @RequestParam Integer returnedTimesInDays){
        return new ResponseEntity<>(bookInfoService.takeBook(bookId, returnedTimesInDays), HttpStatus.OK);
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<BookInfoDto> returnBook(@PathVariable("id") Long bookId){
        return new ResponseEntity<>(bookInfoService.returnBook(bookId), HttpStatus.OK);
    }
}
