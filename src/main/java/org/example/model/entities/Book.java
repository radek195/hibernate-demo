package org.example.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
public class Book {
    @Id
    private final int id;
    @NotNull
    private final String title;
    private String author;
    private int pages;
    private double price;
}
