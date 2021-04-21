package com.carbon.urlshortener.api;


import lombok.Data;

@Data
public class ErrorResponse {
    private String code = "E01";
    private String message;

    public ErrorResponse(Exception exception) {
        code = exception.getClass().getSimpleName();
        message = exception.getMessage();
    }


    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String code, String message) {
        this.message = message;
        this.code = code;
    }
}
