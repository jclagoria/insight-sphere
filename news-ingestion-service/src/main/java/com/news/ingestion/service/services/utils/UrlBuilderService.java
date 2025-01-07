package com.news.ingestion.service.services.utils;

import com.news.ingestion.service.dto.SearchParameterNews;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

public class UrlBuilderService {

    private UrlBuilderService() {}

    public static String buildUrl(SearchParameterNews params, String urlEndPoint, int page, int pageSize) {
        return UriComponentsBuilder
                .fromUriString(urlEndPoint)
                .queryParam("q", params.query())
                .queryParamIfPresent("searchIn", Optional.ofNullable(params.searchIn()))
                .queryParamIfPresent("sources", Optional.ofNullable(params.sources()))
                .queryParamIfPresent("domains", Optional.ofNullable(params.domains()))
                .queryParamIfPresent("excludeDomains", Optional.ofNullable(params.excludeDomains()))
                .queryParamIfPresent("from", Optional.ofNullable(params.from()))
                .queryParamIfPresent("to", Optional.ofNullable(params.to()))
                .queryParamIfPresent("language", Optional.ofNullable(params.language()))
                .queryParamIfPresent("sortBy", Optional.ofNullable(params.sortBy()))
                .queryParam("pageSize", pageSize)
                .queryParam("page", page)
                .toUriString();
    }

}
