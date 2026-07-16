package com.example.e_learning_management.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.e_learning_management.dto.common.ApiResponse;
import com.example.e_learning_management.message.CommonMessage;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(
            ResourceNotFoundException ex
    ) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(Map.ofEntries(
                    Map.entry("notFound", ex.getMessage())
                )));

    }

    @ExceptionHandler(InvalidRangeException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidRange(
        InvalidRangeException ex
    ) {

        return ResponseEntity.badRequest()
                .body(ApiResponse.fail(Map.ofEntries(
                    Map.entry("invalidRange", ex.getMessage())
                )));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> errorDetailMap = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError fieldError) {
                String fieldName = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();

                if (fieldError.getCode() != null && fieldError.getCode().equals("typeMismatch")) {
                    errorDetailMap.put(fieldName, CommonMessage.DATA_BINDING_EXCEPTION);
                } else {
                    errorDetailMap.put(fieldName, defaultMessage);
                }
            } else {
                errorDetailMap.put(error.getObjectName(), error.getDefaultMessage());
            }
        });

        return ResponseEntity.badRequest()
                .body(ApiResponse.fail(
                    errorDetailMap
                ));
    }

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
