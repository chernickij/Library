package com.chernickij.bookservice.service.impl;

import com.chernickij.bookservice.dto.SaveGenreDto;
import com.chernickij.bookservice.exception.ResourceAlreadyExist;
import com.chernickij.bookservice.exception.ResourceNotFoundException;
import com.chernickij.bookservice.model.Genre;
import com.chernickij.bookservice.repository.GenreRepository;
import com.chernickij.bookservice.service.GenreService;
import com.chernickij.bookservice.dto.GenreDto;
import com.chernickij.bookservice.mapper.GenreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.text.MessageFormat.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> getAll(Integer offset, Integer limit) {
        log.debug("Executing the genreService.getAll method with offset = %d and limit = %d".formatted(offset, limit));

        Page<Genre> pagedResult = genreRepository.findAll(PageRequest.of(offset, limit));

        if (pagedResult.hasContent()) {
            return pagedResult.getContent().stream().map(genreMapper::mapToGenreDto).toList();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public GenreDto getById(long id) {
        log.debug("Executing the genreService.getById method with id {}", id);

        return genreRepository.findById(id)
                .map(genreMapper::mapToGenreDto)
                .orElseThrow(() -> new ResourceNotFoundException(format("Genre with id {0} not found ", id)));
    }

    @Override
    @Transactional
    public GenreDto save(SaveGenreDto genreDto) {
        log.debug("Executing the genreService.save method for genre with name: %s".formatted(genreDto.getName()));

        genreRepository.findByName(genreDto.getName())
                .ifPresent(genre -> {
                    throw new ResourceAlreadyExist(MessageFormat.format("Genre with name {0} already exist", genreDto.getName()));
                });

        return genreMapper.mapToGenreDto(genreRepository.save(Genre.builder()
                .name(genreDto.getName())
                .created(new Date())
                .build()));
    }

    @Override
    @Transactional
    public GenreDto update(long id, SaveGenreDto genreDto) {
        log.debug("Executing the genreService.update method for genre with id: %s".formatted(id));

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Genre with id {0} not found ", id)));

        genre.setName(genreDto.getName());
        genre.setUpdated(new Date());

        return genreMapper.mapToGenreDto(genreRepository.save(genre));
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.debug("Executing the genreService.delete method for genre with id: %s".formatted(id));

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Genre with id {0} not found ", id)));
        genreRepository.delete(genre);
    }
}
