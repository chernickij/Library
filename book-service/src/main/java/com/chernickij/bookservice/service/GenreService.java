package com.chernickij.bookservice.service;

import com.chernickij.bookservice.dto.GenreDto;
import com.chernickij.bookservice.dto.SaveGenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAll(Integer offset, Integer limit);

    GenreDto getById(long id);

    GenreDto save(SaveGenreDto genreDto);

    GenreDto update(long id, SaveGenreDto genreDto);

    void delete(long id);
}
