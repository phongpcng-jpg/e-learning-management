package com.example.e_learning_management.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.e_learning_management.dto.common.ApiResponse;
import com.example.e_learning_management.message.CommonMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            Exception ex
    ) {

        return ResponseEntity.internalServerError()
                .body(ApiResponse.fail(Map.ofEntries(
                    Map.entry("fail", CommonMessage.EXCEPTION_HANDLE)
                )));
    }

}
