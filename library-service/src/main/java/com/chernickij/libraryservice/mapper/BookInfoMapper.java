package com.chernickij.libraryservice.mapper;

import com.chernickij.libraryservice.dto.BookInfoDto;
import com.chernickij.libraryservice.model.BookInfo;
import org.mapstruct.Mapper;

@Mapper
public interface BookInfoMapper {

    BookInfo mapToBookInfo(BookInfoDto bookInfoDto);

    BookInfoDto mapToBookInfoDto(BookInfo bookInfo);
}
