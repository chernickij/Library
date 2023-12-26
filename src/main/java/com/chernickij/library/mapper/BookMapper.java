package com.chernickij.library.mapper;

import com.chernickij.library.model.Book;
import com.chernickij.library.dto.BookDto;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

    Book mapToBook(BookDto bookDto);

    BookDto mapToBookDto(Book book);
}
