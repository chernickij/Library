package com.chernickij.library.mapper;

import com.chernickij.library.model.Author;
import com.chernickij.library.dto.AuthorDto;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {

    Author mapToAuthor(AuthorDto authorDto);

    AuthorDto mapToAuthorDto(Author author);
}
