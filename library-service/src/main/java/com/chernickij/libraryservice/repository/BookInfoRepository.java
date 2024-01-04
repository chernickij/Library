package com.chernickij.libraryservice.repository;

import com.chernickij.libraryservice.model.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {

    Optional<BookInfo> findByBookId(long bookId);
}
