package com.chernickij.bookservice.mapper;

import com.chernickij.bookservice.model.Author;
import com.chernickij.bookservice.dto.AuthorDto;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {

    Author mapToAuthor(AuthorDto authorDto);

    AuthorDto mapToAuthorDto(Author author);
}
