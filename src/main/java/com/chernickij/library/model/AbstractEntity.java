package com.chernickij.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractEntity {
    @NotNull
    @Column(name = "ts_created", updatable = false, nullable = false)
    private Date created = new Date();

    @Column(name = "ts_updated")
    private Date updated;
}
