package com.chernickij.libraryservice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoDto {
    private long bookId;
    private LocalDate startTime;
    private LocalDate finishTime;
}
