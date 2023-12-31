package com.chernickij.bookservice.mapper;

import com.chernickij.bookservice.model.Genre;
import com.chernickij.bookservice.dto.GenreDto;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper {

    Genre mapToGenre(GenreDto genreDto);

    GenreDto mapToGenreDto(Genre genre);
}
