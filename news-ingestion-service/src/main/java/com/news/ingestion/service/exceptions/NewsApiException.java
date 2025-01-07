package com.news.ingestion.service.exceptions;

import lombok.Getter;

@Getter
public class NewsApiException extends RuntimeException {

    private String errorMessage;
    private String code;
    private String status;
    private int httpStatusCode;

    public NewsApiException(String errorMessage, String message, String code, String status, int httpStatusCode) {
        super(message);
        this.code = code;
        this.status = status;
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
    }
}
