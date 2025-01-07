package com.news.ingestion.service.services.mapper;

import com.news.ingestion.service.model.api.ArticleItem;
import com.news.ingestion.service.model.mongo.NewRaw;
import com.news.ingestion.service.model.mongo.SourceRaw;

public class ArticleMapper {

    private ArticleMapper() {}

    public static NewRaw toNewsRaw(ArticleItem articleItem) {
        SourceRaw source = new SourceRaw();
        source.setId(articleItem.getSource().getId());
        source.setName(articleItem.getSource().getName());

        NewRaw newRaw = new NewRaw();
        newRaw.setSourceRaws(source);
        newRaw.setAuthor(articleItem.getAuthor());
        newRaw.setTitle(articleItem.getTitle());
        newRaw.setDescription(articleItem.getDescription());
        newRaw.setUrl(articleItem.getUrl());
        newRaw.setUrlToImage(articleItem.getUrlToImage());
        newRaw.setContent(articleItem.getContent());
        newRaw.setPublishedDate(articleItem.getPublishedAt());

        return newRaw;
    }

}
