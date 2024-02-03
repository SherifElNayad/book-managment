package com.example.bookmanagment.Entities.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;

@Data
public class BookRequest {
    @NotBlank(message = "title is mandatory")
    private String title;

    @NotBlank(message = "Author name is mandatory")
    private String author;

    @NotBlank(message = "Publish date is mandatory")
    private Instant publishDate;

    @NotBlank(message = "Category is mandatory")
    private long categoryId;

    private Boolean isAv;

    private String shortDescription;

}
