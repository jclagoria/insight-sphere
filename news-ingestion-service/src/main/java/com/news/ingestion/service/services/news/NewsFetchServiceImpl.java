package com.news.ingestion.service.services.news;

import com.news.ingestion.service.dto.SearchParameterNews;
import com.news.ingestion.service.model.api.ArticleItem;
import com.news.ingestion.service.model.api.NewsResult;
import com.news.ingestion.service.services.api.ApiNewsManageFetchService;
import com.news.ingestion.service.services.utils.UrlBuilderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class NewsFetchServiceImpl implements NewsFetchService {

    private final ApiNewsManageFetchService apiNewsManageFetchService;

    /**
     * Fetches the total number of news results for a given search parameter.
     *
     * @param searchParameterNews the search parameters for querying the news API
     * @return a Mono emitting the total number of results
     */
    @Override
    public Mono<Long> fetchTotalResults(SearchParameterNews searchParameterNews) {
        String urlSearch = UrlBuilderService.buildUrl(searchParameterNews, "/everything", 1, 1);

        return apiNewsManageFetchService
                .fetchMonoObject(urlSearch, NewsResult.class)
                .map(NewsResult::getTotalResults)
                .doOnNext(totalResults -> log.info("Total results fetched: {}", totalResults))
                .onErrorResume(e -> {
                    log.error("Error fetching total results", e);
                    return Mono.just(0L);
                });
    }

    /**
     * Fetches a page of articles based on the given search parameters, page, and page size.
     *
     * @param searchParameterNews the search parameters for querying the news API
     * @param page the page number to fetch
     * @param pageSize the number of articles per page
     * @return a Flux emitting the articles for the requested page
     */
    @Override
    public Flux<ArticleItem> fetchPageData(SearchParameterNews searchParameterNews, int page, int pageSize) {
        String urlSearch = UrlBuilderService.buildUrl(searchParameterNews, "/everything", page, pageSize);

        return apiNewsManageFetchService.fetchMonoObject(urlSearch, NewsResult.class)
                .flatMapMany(newsResult -> {
                    if (newsResult == null || newsResult.getArticles() == null) {
                        log.warn("No articles found for page {}", page);
                        return Flux.empty();
                    }
                    return Flux.fromIterable(newsResult.getArticles());
                })
                .onErrorResume(e -> {
                    log.error("Error fetching page data for page {}", page, e);
                    return Flux.empty();
                });
    }
}
