package com.news.ingestion.service.controller;

import com.news.ingestion.service.dto.SearchParameterNews;
import com.news.ingestion.service.services.NewsIngestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/mews", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class NewsController {

    private NewsIngestionService newsIngestionService;

    @GetMapping("/ingest")
    public Mono<Void> ingestNews(@RequestParam String query) {

        SearchParameterNews params = new SearchParameterNews(query, null, null, null,
                null, null, null, null, null, 0, 0);

        return newsIngestionService.fetchNewsIngestion(params);
    }

}
