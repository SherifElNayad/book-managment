package com.example.bookmanagment.Entities.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class BorrowRequestBody {
    @NotNull
    private long bookId;
    @NotNull
    private Instant startDate;
    @Future(message = "Invalid date")
    private Instant endDate;
    @NotNull
    private long userId;
}
