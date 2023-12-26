package com.chernickij.library.model;

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
@Table(name = "privilege")
@EqualsAndHashCode(callSuper = true)
public class Privilege extends AbstractEntity {
    @Id
    @SequenceGenerator(name = "privilege_id_seq", sequenceName = "privilege_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "privilege_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
