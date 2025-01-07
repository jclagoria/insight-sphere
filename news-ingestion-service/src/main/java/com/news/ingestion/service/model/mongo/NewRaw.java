package com.news.ingestion.service.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("news_raw")
@Data
public class NewRaw {

    @Id
    private String id;

    private SourceRaw sourceRaws;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private Date publishedDate;
    private String content;

}
