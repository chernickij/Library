package com.chernickij.bookservice.mapper;

import com.chernickij.bookservice.model.Book;
import com.chernickij.bookservice.dto.BookDto;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

    Book mapToBook(BookDto bookDto);

    BookDto mapToBookDto(Book book);
}
