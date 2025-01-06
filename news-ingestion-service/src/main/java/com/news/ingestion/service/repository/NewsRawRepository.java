package com.news.ingestion.service.repository;

import com.news.ingestion.service.model.mongo.NewRaw;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRawRepository extends ReactiveMongoRepository<NewRaw, String> {
}
