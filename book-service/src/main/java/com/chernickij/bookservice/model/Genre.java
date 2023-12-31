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
@Table(name = "genre")
@EqualsAndHashCode(callSuper = true)
public class Genre extends AbstractEntity {
    @Id
    @SequenceGenerator(name = "genre_id_seq", sequenceName = "genre_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "genre_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;

    @NotNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
