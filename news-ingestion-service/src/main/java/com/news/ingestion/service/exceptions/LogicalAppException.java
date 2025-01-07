package com.news.ingestion.service.exceptions;

import lombok.Getter;

@Getter
public class LogicalAppException extends RuntimeException {
    private final String errorMessage;
    private final boolean status;

    public LogicalAppException(String message, String errorMessage, boolean status) {
        super(message);
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
