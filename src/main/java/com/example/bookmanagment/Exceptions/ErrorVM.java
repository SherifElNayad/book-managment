package com.example.bookmanagment.Exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorVM {
    private Integer errorKey;
    private String errorMessage;
}
