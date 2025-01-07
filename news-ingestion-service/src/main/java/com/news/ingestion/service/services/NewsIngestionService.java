package com.news.ingestion.service.services;

import com.news.ingestion.service.dto.IngestionResponse;
import com.news.ingestion.service.dto.SearchParameterNews;
import reactor.core.publisher.Mono;

public interface NewsIngestionService {

    Mono<IngestionResponse> fetchNewsIngestionBatch(SearchParameterNews searchParameterNews);

}
