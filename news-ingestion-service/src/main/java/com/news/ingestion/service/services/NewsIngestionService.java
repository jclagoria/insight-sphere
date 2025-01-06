package com.news.ingestion.service.services;

import com.news.ingestion.service.dto.SearchParameterNews;
import reactor.core.publisher.Mono;

public interface NewsIngestionService {

    Mono<Void> fetchNewsIngestion(SearchParameterNews searchParameterNews);

}
