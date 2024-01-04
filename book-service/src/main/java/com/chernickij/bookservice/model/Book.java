package com.chernickij.bookservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
@EqualsAndHashCode(callSuper = true)
public class Book extends AbstractEntity {
    @Id
    @SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "book_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;

    @NotNull
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}
