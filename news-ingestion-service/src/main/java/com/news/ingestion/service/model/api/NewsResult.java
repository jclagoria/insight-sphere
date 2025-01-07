package com.news.ingestion.service.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewsResult {

    @JsonProperty
    private String status;

    @JsonProperty
    private long totalResults;

    @JsonProperty
    private List<ArticleItem> articles;

}
