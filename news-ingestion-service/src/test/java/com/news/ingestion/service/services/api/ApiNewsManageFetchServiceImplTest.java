package com.news.ingestion.service.services.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.ingestion.service.dto.ApiNewsErrorItem;
import com.news.ingestion.service.exceptions.NewsApiException;
import com.news.ingestion.service.model.api.ArticleItem;
import com.news.ingestion.service.model.api.NewsResult;
import com.news.ingestion.service.model.api.Source;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiNewsManageFetchServiceImplTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ApiNewsManageFetchServiceImpl service;

    @Test
    void fetchMonoObject_SuccessfulResponse() throws Exception {
        // Mocking the successful response
        String url = "http://example.com/api/resource";

        Source itemSource = new Source("source1", "Source 1");
        ArticleItem item = new ArticleItem(
                itemSource, "author A", "title AB", "Here is the description of the articule A",
                "Here is the description of the articule A", "http://url.image.jpg", null,
                "Content of the article, is more large."
        );
        NewsResult resultMock = new NewsResult("ok", 1, List.of(item));

        when(webClient.get()).thenAnswer(invocation -> requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(url)).thenAnswer(invocation -> requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenAnswer(invocation -> responseSpec);

        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(NewsResult.class)).thenReturn(Mono.just(resultMock));

        // Act
        Mono<NewsResult> resultResponse = service.fetchMonoObject(url, NewsResult.class);

        // Assert
        StepVerifier.create(resultResponse)
                .expectNextMatches(response -> "ok".equals(response.getStatus())).verifyComplete();
    }

    @Test
    void fetchMonoObject_ErrorResponseWithParsing() throws Exception {
        String url = "http://example.com/api/resource";

        // Mock the WebClient method chain
        when(webClient.get()).thenAnswer(invocation -> requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(url)).thenAnswer(invocation -> requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenAnswer(invocation -> responseSpec);

        // Mock onStatus to return responseSpec (continue the method chain)
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

        // Mock bodyToMono to return Mono.error with the custom exception
        when(responseSpec.bodyToMono(ArgumentMatchers.<Class<?>>any()))
                .thenReturn(Mono.error(new NewsApiException("Error", "Not Found", "404", "NOT_FOUND", 404)));

        // Act
        Mono<NewsResult> result = service.fetchMonoObject(url, NewsResult.class);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof NewsApiException &&
                        "404".equals(((NewsApiException) throwable).getCode()))
                .verify();
    }

    @Test
    void fetchMonoObject_ErrorResponseWithParseError() throws Exception {
        String url = "http://example.com/api/resource";
        String errorBody = "Invalid JSON";

        // Mock the WebClient method chain
        when(webClient.get()).thenAnswer(invocation -> requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(url)).thenAnswer(invocation -> requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenAnswer(invocation -> responseSpec);

        // Mock onStatus to return responseSpec (continue the method chain)
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

        // Mock bodyToMono to return Mono.error with the custom exception
        when(responseSpec.bodyToMono(ArgumentMatchers.<Class<?>>any()))
                .thenReturn(Mono.error(new NewsApiException("Error", "Parse Error",
                        "Unable to parse error body",
                        "Unable to parse error body",
                        500)));

        // Act
        Mono<NewsResult> result = service.fetchMonoObject(url, NewsResult.class);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof NewsApiException &&
                        "Unable to parse error body".equals(((NewsApiException) throwable).getCode()))
                .verify();
    }

}