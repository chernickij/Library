package com.chernickij.library.mapper;

import com.chernickij.library.model.Genre;
import com.chernickij.library.dto.GenreDto;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper {

    Genre mapToGenre(GenreDto genreDto);

    GenreDto mapToGenreDto(Genre genre);
}
