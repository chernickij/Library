package com.chernickij.bookservice.service.impl;

import com.chernickij.bookservice.dto.SaveAuthorDto;
import com.chernickij.bookservice.exception.ResourceAlreadyExist;
import com.chernickij.bookservice.exception.ResourceNotFoundException;
import com.chernickij.bookservice.model.Author;
import com.chernickij.bookservice.repository.AuthorRepository;
import com.chernickij.bookservice.service.AuthorService;
import com.chernickij.bookservice.dto.AuthorDto;
import com.chernickij.bookservice.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.text.MessageFormat.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> getAll(Integer offset, Integer limit) {
        log.debug("Executing the authorService.getAll method with offset = %d and limit = %d".formatted(offset, limit));

        Page<Author> pagedResult = authorRepository.findAll(PageRequest.of(offset, limit));

        return pagedResult.hasContent()
                ? pagedResult.getContent().stream().map(authorMapper::mapToAuthorDto).toList()
                : new ArrayList<>();
    }

    @Override
    public AuthorDto getById(long id) {
        log.debug("Executing the authorService.getById method with id {}", id);

        return authorRepository.findById(id)
                .map(authorMapper::mapToAuthorDto)
                .orElseThrow(() -> new ResourceNotFoundException(format("Author with id {0} not found ", id)));
    }

    @Override
    @Transactional
    public AuthorDto save(SaveAuthorDto authorDto) {
        log.debug("Executing the authorService.save method for author with name: %s %s".formatted(authorDto.getFirstName(), authorDto.getLastName()));

        authorRepository.findByFirstNameAndLastName(authorDto.getFirstName(), authorDto.getLastName())
                .ifPresent(author -> {
                    throw new ResourceAlreadyExist(format("Author with name {0} {1} already exist", authorDto.getFirstName(), authorDto.getLastName()));
                });

        return authorMapper.mapToAuthorDto(authorRepository.save(Author.builder()
                .firstName(authorDto.getFirstName())
                .created(new Date())
                .lastName(authorDto.getLastName())
                .build()));
    }

    @Override
    @Transactional
    public AuthorDto update(long id, SaveAuthorDto authorDto) {
        log.debug("Executing the authorService.save method for author with id: %s".formatted(id));

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Author with id {0} not found ", id)));

        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setUpdated(new Date());

        return authorMapper.mapToAuthorDto(authorRepository.save(author));
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.debug("Executing the authorService.delete method for author with id: %s".formatted(id));

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Author with id {0} not found ", id)));
        authorRepository.delete(author);
    }
}
