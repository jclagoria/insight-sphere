package com.news.ingestion.service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpHeaders;

@Configuration
@EnableWebFlux
@Slf4j
public class WebClientConfig implements WebFluxConfigurer {

    @Value("${endpoint.newsapi.baseUrl}")
    private String urlBaseAPI;

    @Value("${endpoint.newsapi.apiKey}")
    private String apiKey;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(urlBaseAPI)
                .defaultHeader("X-Api-Key", apiKey)
                .filter((request, next) -> {
                    log.info("Request: {} {}", request.method(), request.url());
                    return next.exchange(request)
                            .doOnSuccess(response -> log.info("Response Status: {}",
                                    response.statusCode()));
                })
                .build();
    }
}
