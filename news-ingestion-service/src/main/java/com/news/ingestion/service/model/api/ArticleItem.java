package com.news.ingestion.service.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArticleItem {

    @JsonProperty
    private Source source;

    @JsonProperty
    private String author;

    @JsonProperty
    private String title;

    @JsonProperty
    private String description;

    @JsonProperty
    private String url;

    @JsonProperty
    private String urlToImage;

    @JsonProperty
    private Date   publishedAt;

    @JsonProperty
    private String content;

}
