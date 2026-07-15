package com.example.e_learning_management.exception;

import lombok.Getter;


@Getter
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
