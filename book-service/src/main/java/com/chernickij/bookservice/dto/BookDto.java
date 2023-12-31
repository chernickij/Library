package com.chernickij.bookservice.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private long id;
    private String isbn;
    private String name;
    private String description;
    private AuthorDto author;
    private GenreDto genre;
}
