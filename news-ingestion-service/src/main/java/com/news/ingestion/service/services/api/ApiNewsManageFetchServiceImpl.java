package com.news.ingestion.service.services.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.ingestion.service.dto.ApiNewsErrorItem;
import com.news.ingestion.service.exceptions.NewsApiException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class ApiNewsManageFetchServiceImpl implements ApiNewsManageFetchService {

    private static final String ERROR_API_CALL = "Error during API call";
    private static final String PARSE_ERROR_MESSAGE = "Parse Error";
    private static final String UNABLE_TO_PARSE_ERROR = "Error during API Call: Unable to parse error body";

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    /**
     * Fetches a Mono of a specified response type from the provided URL.
     *
     * @param urlWithParameters The complete URL with query parameters.
     * @param responseType      The class type of the expected response.
     * @param <T>               The type of the response object.
     * @return Mono containing the response object of the specified type.
     */
    @Override
    public <T> Mono<T> fetchMonoObject(String urlWithParameters, Class<T> responseType) {
        return webClient.get()
                .uri(urlWithParameters)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class).flatMap(body -> {
                            log.error("{}: Status {} Body: {}", ERROR_API_CALL, response.statusCode(), body);

                            try {
                                ApiNewsErrorItem errorResponse = objectMapper.readValue(body, ApiNewsErrorItem.class);
                                log.error("Error when try to call the API {}: {}", ERROR_API_CALL, errorResponse);

                                return Mono.error(new NewsApiException(
                                        ERROR_API_CALL,
                                        errorResponse.message(),
                                        errorResponse.code(),
                                        errorResponse.status(),
                                        response.statusCode().value())
                                );
                            } catch (JsonProcessingException e) {
                                log.error("Error parsing response body: {}", e.getMessage());
                                return Mono.error(new NewsApiException(
                                        ERROR_API_CALL,
                                        PARSE_ERROR_MESSAGE,
                                        UNABLE_TO_PARSE_ERROR,
                                        UNABLE_TO_PARSE_ERROR,
                                        HttpStatus.INTERNAL_SERVER_ERROR.value()
                                ));
                            }
                        }))
                .bodyToMono(responseType)
                .doOnError(e -> log.error("Error when try to call the API {}: {}", ERROR_API_CALL, e.getMessage()));
    }

}
