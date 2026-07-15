package com.example.e_learning_management.common.response;

import java.util.Map;

import com.example.e_learning_management.common.message.CommonMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private Boolean success;

    private Map<String, String> messages;

    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .messages(Map.ofEntries(
                    Map.entry("success", CommonMessage.SUCCESS)
                ))
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(Map<String, String> messages, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .messages(messages)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> fail(Map<String, String> messages) {
        return ApiResponse.<T>builder()
                .success(false)
                .messages(messages)
                .build();
    }

    public static <T> ApiResponse<T> fail(Map<String, String> messages, T data) {

        return ApiResponse.<T>builder()
                .success(false)
                .messages(messages)
                .data(data)
                .build();
    }

}
