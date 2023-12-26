package com.chernickij.library.service.impl;

import com.chernickij.library.dto.SaveBookDto;
import com.chernickij.library.exception.ResourceAlreadyExist;
import com.chernickij.library.exception.ResourceNotFoundException;
import com.chernickij.library.model.Author;
import com.chernickij.library.model.Book;
import com.chernickij.library.model.Genre;
import com.chernickij.library.repository.AuthorRepository;
import com.chernickij.library.repository.BookRepository;
import com.chernickij.library.repository.GenreRepository;
import com.chernickij.library.service.BookService;
import com.chernickij.library.dto.BookDto;
import com.chernickij.library.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAll(Integer offset, Integer limit) {
        log.debug("Executing the bookService.getAll method with offset = %d and limit = %d".formatted(offset, limit));

        Page<Book> pagedResult = bookRepository.findAll(PageRequest.of(offset, limit));

        return pagedResult.hasContent()
                ? pagedResult.getContent().stream().map(bookMapper::mapToBookDto).toList()
                : new ArrayList<>();
    }

    @Override
    public BookDto getById(long id) {
        log.debug("Executing the bookService.getById method with id {}", id);

        return bookRepository.findById(id)
                .map(bookMapper::mapToBookDto)
                .orElseThrow(() -> new ResourceNotFoundException(format("Book with id {0} not found ", id)));
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        log.debug("Executing the bookService.getByIsbn method with ISBN {}", isbn);
        return bookRepository.findByIsbn(isbn)
                .map(bookMapper::mapToBookDto)
                .orElseThrow(() -> new ResourceNotFoundException(format("Book with ISBN {0} not found ", isbn)));
    }

    @Override
    @Transactional
    public BookDto save(SaveBookDto bookDto) {
        log.debug("Executing the bookService.save method for book with ISBN: %s".formatted(bookDto.getIsbn()));

        bookRepository.findByIsbn(bookDto.getIsbn()).ifPresent(book -> {
            throw new ResourceAlreadyExist(MessageFormat.format("Book with ISBN {0} already exist", bookDto.getIsbn()));
        });

        Author author = getAuthor(bookDto.getAuthor().getId());
        Genre genre = getGenre( bookDto.getGenre().getId());

        return bookMapper.mapToBookDto(bookRepository.save(Book.builder()
                .isbn(bookDto.getIsbn())
                .name(bookDto.getName())
                .description(bookDto.getDescription())
                .author(author)
                .genre(genre)
                .build()));
    }

    @Override
    @Transactional
    public BookDto update(long id, SaveBookDto bookDto) {
        log.debug("Executing the bookService.save method for book with id: %s".formatted(id));

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Book with id {0} not found ", id)));

        Author author = getAuthor(bookDto.getAuthor().getId());
        Genre genre = getGenre( bookDto.getGenre().getId());

        book.setIsbn(bookDto.getIsbn());
        book.setName(bookDto.getName());
        book.setDescription(bookDto.getDescription());
        book.setAuthor(author);
        book.setGenre(genre);

        return bookMapper.mapToBookDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.debug("Executing the bookService.delete method for book with id: %s".formatted(id));

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Book with id {0} not found ", id)));
        bookRepository.delete(book);
    }

    private Author getAuthor(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        authorOptional.orElseThrow(() -> new ResourceNotFoundException(format("Author with id {0} not found ", id)));
        return authorOptional.get();
    }

    private Genre getGenre(Long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        genreOptional.orElseThrow(() -> new ResourceNotFoundException(format("Genre with id {0} not found ", id)));
        return genreOptional.get();
    }
}
