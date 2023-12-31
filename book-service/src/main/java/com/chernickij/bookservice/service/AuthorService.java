package com.chernickij.bookservice.service;

import com.chernickij.bookservice.dto.AuthorDto;
import com.chernickij.bookservice.dto.SaveAuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAll(Integer offset, Integer limit);

    AuthorDto getById(long id);

    AuthorDto save(SaveAuthorDto authorDto);

    AuthorDto update(long id, SaveAuthorDto authorDto);

    void delete(long id);
}
