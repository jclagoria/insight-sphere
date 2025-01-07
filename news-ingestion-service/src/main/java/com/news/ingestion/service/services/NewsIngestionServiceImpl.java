package com.news.ingestion.service.services;

import com.news.ingestion.service.dto.IngestionResponse;
import com.news.ingestion.service.dto.SearchParameterNews;
import com.news.ingestion.service.exceptions.LogicalAppException;
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
    public Mono<IngestionResponse> fetchNewsIngestionBatch(SearchParameterNews searchParameterNews) {

        return newsFetchService.fetchTotalResults(searchParameterNews)
                .flatMapMany(totalResults -> {
                    int pageSize = searchParameterNews.pageSize() > 0 ? searchParameterNews.pageSize() : 50;
                    int totalPages = (int) Math.ceil((double) totalResults / pageSize);

                    logger.info("Total results: {}, Total pages: {}", totalResults, totalPages);

                    return Flux.range(1, pageSize)
                            .flatMap(page -> newsFetchService.fetchPageData(searchParameterNews, page, pageSize));
                })
                .map(ArticleMapper::toNewsRaw)
                .buffer(500) // Agrupa los registros en lotes de 500
                .flatMap(batch -> newsRawRepository.saveAll(batch)
                        .then(Mono.just(batch.size())) // Retorna el tamaño del lote procesado
                )
                .reduce(0, Integer::sum) // Suma el tamaño de todos los lotes procesados
                .map(recordsInserted -> {
                    logger.info("Data ingestion completed. Records inserted: {}", recordsInserted);
                    return new IngestionResponse(true, recordsInserted);
                })
                .onErrorResume(e -> {
                    logger.error("Data ingestion failed", e);
                    return Mono.just(new IngestionResponse(false, 0));
                })
                .onErrorMap(ex -> {
                    logger.error("Data ingestion failed: ", ex);
                    return new LogicalAppException("Data ingestion failed", ex.getMessage(), false);
                });
    }

}
