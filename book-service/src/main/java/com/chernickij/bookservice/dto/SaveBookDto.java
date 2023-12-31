package com.chernickij.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SaveBookDto {
    private String isbn;
    private String name;
    private String description;
    private AuthorDto author;
    private GenreDto genre;
}
