package com.news.ingestion.service.services;

import com.news.ingestion.service.dto.SearchParameterNews;
import com.news.ingestion.service.repository.NewsRawRepository;
import com.news.ingestion.service.services.mapper.ArticleMapper;
import com.news.ingestion.service.services.news.NewsFetchService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class NewsIngestionServiceImpl implements NewsIngestionService {

    private final Logger logger = LoggerFactory.getLogger(NewsIngestionServiceImpl.class);

    private final NewsFetchService newsFetchService;
    private final NewsRawRepository newsRawRepository;

    @Override
    public Mono<Void> fetchNewsIngestion(SearchParameterNews searchParameterNews) {

        return newsFetchService.fetchTotalResults(searchParameterNews)
                .flatMapMany(totalResults -> {
                    int pageSize = searchParameterNews.pageSize() > 0 ? searchParameterNews.pageSize() : 50;
                    int totalPages = (int) Math.ceil((double) totalResults / pageSize);

                    logger.info("Total results: {}, Total pages: {}", totalResults, totalPages);

                    return Flux.range(1, totalPages)
                            .flatMap(page -> newsFetchService.fetchPageData(searchParameterNews, page, pageSize));
                })
                .map(ArticleMapper::toNewsRaw)
                .buffer(500)
                .flatMap(newsRawRepository::saveAll)
                .doOnComplete(() -> logger.info("Data ingestion completed"))
                .then();
    }

}
