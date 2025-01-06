package com.news.ingestion.service.services.database;

import com.news.ingestion.service.repository.NewsRawRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class NewRawServiceImpl implements NewRawService {

    private NewsRawRepository newsRawRepository;

    @Override
    public Mono<Void> deleteAllNewsRar() {
        return newsRawRepository.deleteAll();
    }
}
