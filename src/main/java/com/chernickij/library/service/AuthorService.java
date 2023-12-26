package com.chernickij.library.service;

import com.chernickij.library.dto.AuthorDto;
import com.chernickij.library.dto.SaveAuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAll(Integer offset, Integer limit);

    AuthorDto getById(long id);

    AuthorDto save(SaveAuthorDto authorDto);

    AuthorDto update(long id, SaveAuthorDto authorDto);

    void delete(long id);
}
