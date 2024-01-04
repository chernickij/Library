package com.chernickij.libraryservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_info")
@EqualsAndHashCode
public class BookInfo {

    @Id
    @SequenceGenerator(name = "book_info_id_seq", sequenceName = "book_info_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "book_info_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "book_id", nullable = false)
    private long bookId;

    @Column(name = "taken_time")
    private LocalDate startTime;

    @Column(name = "passed_time")
    private LocalDate finishTime;

    @NotNull
    @Column(name = "ts_created", updatable = false, nullable = false)
    private Date created = new Date();

    @Column(name = "ts_updated")
    private Date updated;
}
