package com.chernickij.library.service;

import com.chernickij.library.dto.BookDto;
import com.chernickij.library.dto.SaveBookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAll(Integer offset, Integer limit);

    BookDto getById(long id);

    BookDto getByIsbn(String isbn);

    BookDto save(SaveBookDto bookDto);

    BookDto update(long id, SaveBookDto bookDto);

    void delete(long id);
}
