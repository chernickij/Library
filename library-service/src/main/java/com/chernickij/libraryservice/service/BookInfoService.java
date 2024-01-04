package com.chernickij.libraryservice.service;

import com.chernickij.libraryservice.dto.BookInfoDto;

import java.util.List;

public interface BookInfoService {
    List<BookInfoDto> getAll(Integer offset, Integer limit);

    BookInfoDto getByBookId(long id);

    BookInfoDto save(long bookId);

    BookInfoDto takeBook(long bookId, int returnedTimeInDays);

    BookInfoDto returnBook(long bookId);

}
