package com.news.ingestion.service.dto;

public record IngestionResponse(boolean success, long recordsInserted) {
}
