package com.news.ingestion.service.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NewsApiException.class)
    public ResponseEntity<Map<String, String>> handleNewsApiException(NewsApiException ex) {
        Map<String, String> bodyError = new HashMap<>();
        bodyError.put("message", ex.getMessage());
        bodyError.put("status", ex.getStatus());
        bodyError.put("code", ex.getCode());
        bodyError.put("errorMessage", ex.getErrorMessage());
        bodyError.put("timestamp", Instant.now().toString());

        return ResponseEntity.status(ex.getHttpStatusCode()).body(bodyError);
    }

    @ExceptionHandler(LogicalAppException.class)
    public ResponseEntity<Map<String, String>> handleLogicalAppException(LogicalAppException ex) {
        Map<String, String> bodyError = new HashMap<>();
        bodyError.put("message", ex.getMessage());
        bodyError.put("status", String.valueOf(ex.isStatus()));
        bodyError.put("errorMessage", ex.getErrorMessage());
        bodyError.put("timestamp", Instant.now().toString());

        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(bodyError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> bodyError = new HashMap<>();
        bodyError.put("errorMessage", "En unexpected error occurred");
        bodyError.put("message", ex.getMessage());
        bodyError.put("timestamp", Instant.now().toString());

        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(bodyError);
    }

}
