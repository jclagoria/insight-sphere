package com.news.ingestion.service.services.api;

import reactor.core.publisher.Mono;

public interface ApiNewsManageFetchService {

    <T> Mono<T> fetchMonoObject(String urlWithParameters, Class<T> responseType);

}
