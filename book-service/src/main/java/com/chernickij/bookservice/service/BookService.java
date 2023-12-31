package com.chernickij.bookservice.service;

import com.chernickij.bookservice.dto.BookDto;
import com.chernickij.bookservice.dto.SaveBookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAll(Integer offset, Integer limit);

    BookDto getById(long id);

    BookDto getByIsbn(String isbn);

    BookDto save(SaveBookDto bookDto);

    BookDto update(long id, SaveBookDto bookDto);

    void delete(long id);
}
