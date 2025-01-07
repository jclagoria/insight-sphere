package com.news.ingestion.service.dto;

public record SearchParameterNews(String query,
                                  String searchIn,
                                  String sources,
                                  String domains,
                                  String excludeDomains, String from, String to,
                                  String language, String sortBy, int pageSize, int page) {
}
