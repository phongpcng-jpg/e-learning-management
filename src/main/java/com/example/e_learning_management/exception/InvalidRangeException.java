package com.example.e_learning_management.exception;

import lombok.Getter;

@Getter
public class InvalidRangeException extends RuntimeException {

    public InvalidRangeException(String message) {
        super(message);
    }

}
