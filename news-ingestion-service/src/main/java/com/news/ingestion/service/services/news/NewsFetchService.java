package com.news.ingestion.service.services.news;

import com.news.ingestion.service.dto.SearchParameterNews;
import com.news.ingestion.service.model.api.ArticleItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NewsFetchService {

    Mono<Long> fetchTotalResults(SearchParameterNews searchParameterNews);

    Flux<ArticleItem> fetchPageData(SearchParameterNews searchParameterNews, int page, int pageSize);

}
