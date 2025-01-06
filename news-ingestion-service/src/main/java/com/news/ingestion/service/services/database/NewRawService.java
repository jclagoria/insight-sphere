package com.news.ingestion.service.services.database;

import reactor.core.publisher.Mono;

public interface NewRawService {

    Mono<Void> deleteAllNewsRar();

}
