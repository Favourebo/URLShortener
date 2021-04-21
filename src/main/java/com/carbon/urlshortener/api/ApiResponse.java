package com.carbon.urlshortener.api;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private boolean success;

    private T result;

    private ErrorResponse error;

    public ApiResponse(boolean success, T result) {
        this.success = success;
        this.result = result;
    }

    public ApiResponse(boolean success) {
        this.success = success;
    }

    public ApiResponse(ErrorResponse error) {
        this.error = error;
    }
}
