package com.example.bookmanagment.Entities.requests;

import com.example.bookmanagment.Entities.feature.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CategoryRequest {
    private Long id;
    private String name;

}
