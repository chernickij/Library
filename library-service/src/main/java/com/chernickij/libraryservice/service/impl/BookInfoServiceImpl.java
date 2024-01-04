package com.chernickij.libraryservice.service.impl;

import com.chernickij.libraryservice.dto.BookInfoDto;
import com.chernickij.libraryservice.exception.BookInfoConflictException;
import com.chernickij.libraryservice.exception.BookInfoNotFoundException;
import com.chernickij.libraryservice.mapper.BookInfoMapper;
import com.chernickij.libraryservice.model.BookInfo;
import com.chernickij.libraryservice.repository.BookInfoRepository;
import com.chernickij.libraryservice.service.BookInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookInfoServiceImpl implements BookInfoService {

    private final BookInfoRepository bookInfoRepository;
    private final BookInfoMapper bookInfoMapper;

    @Override
    public List<BookInfoDto> getAll(Integer offset, Integer limit) {
        log.debug("Executing the bookInfoService.getAll method with offset = %d and limit = %d".formatted(offset, limit));

        Page<BookInfo> pagedResult = bookInfoRepository.findAll(PageRequest.of(offset, limit));

        return pagedResult.hasContent()
                ? pagedResult.getContent().stream().map(bookInfoMapper::mapToBookInfoDto).toList()
                : new ArrayList<>();
    }

    @Override
    public BookInfoDto getByBookId(long id) {
        log.debug("Executing the bookInfoService.getByBookId method with id {}", id);

        return bookInfoRepository.findByBookId(id)
                .map(bookInfoMapper::mapToBookInfoDto)
                .orElseThrow(() -> new BookInfoNotFoundException(format("Book Info with Book id {0} not found ", id)));
    }

    @Override
    public BookInfoDto save(long bookId) {
        log.debug("Executing the bookService.save method for book with id: %s".formatted(bookId));

        bookInfoRepository.findByBookId(bookId).ifPresent(bookInfo -> {
            throw new BookInfoConflictException(format("Book Info with book id {0} already exist", bookId));
        });

        return bookInfoMapper.mapToBookInfoDto(bookInfoRepository.save(BookInfo.builder()
                .bookId(bookId)
                .created(new Date())
                .build()));
    }

    @Override
    public BookInfoDto takeBook(long bookId, int returnedTimesInDays) {
        log.debug("Executing the bookInfoService.takeBook method for book with id: %s".formatted(bookId));

        BookInfo bookInfo = validateBookInfo(bookId);
        if (bookInfo.getStartTime() != null) {
            throw new BookInfoConflictException(format("Book with id {0} already in use", bookId));
        }

        bookInfo.setStartTime(LocalDate.now());
        bookInfo.setFinishTime(LocalDate.now().plusDays(returnedTimesInDays));
        bookInfo.setUpdated(new Date());
        bookInfoRepository.save(bookInfo);

        return bookInfoMapper.mapToBookInfoDto(bookInfo);
    }

    @Override
    public BookInfoDto returnBook(long bookId) {
        log.debug("Executing the bookInfoService.takeBook method for book with id: %s".formatted(bookId));

        BookInfo bookInfo = validateBookInfo(bookId);

        bookInfo.setStartTime(null);
        bookInfo.setFinishTime(null);
        bookInfo.setUpdated(new Date());
        bookInfoRepository.save(bookInfo);

        return bookInfoMapper.mapToBookInfoDto(bookInfo);
    }

    private BookInfo validateBookInfo(long bookId) {
        Optional<BookInfo> bookInfoOptional = bookInfoRepository.findByBookId(bookId);
        if (bookInfoOptional.isEmpty()) {
            throw new BookInfoNotFoundException(format("Book Info with id {0} not found", bookId));
        }
        return bookInfoOptional.get();
    }
}
