package com.diploma.resource;

import com.diploma.exception.ErrorResponse;
import com.diploma.model.Manufacture;
import com.diploma.model.dto.ManufactureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/api/v1/external/manufacture")
public class ManufactureRequestController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    private static final String UNKNOWN_ERROR_MESSAGE = "Произошла непредвиденная ошибка";

    @PostMapping("/create-product")
    public Mono<ResponseEntity<?>> createProduct(@RequestBody ManufactureDto manufactureDto) {
        WebClient webClient = webClientBuilder.baseUrl("http://MANUFACTURE").build();

        return webClient.post()
                .uri("/api/v1/products")
                .bodyValue(manufactureDto)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(Manufacture.class)
                                .map(product -> ResponseEntity.status(HttpStatus.CREATED).body(product));
                    } else {
                        return response.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR,
                                        UNKNOWN_ERROR_MESSAGE,
                                        HttpStatus.INTERNAL_SERVER_ERROR
                                ))
                                .map(error -> ResponseEntity.status(response.statusCode()).body(error));
                    }
                });
    }

    @DeleteMapping("/delete-product/{product_id}")
    public Mono<ResponseEntity<?>> deleteProduct(@PathVariable("product_id") Long productId) {
        WebClient webClient = webClientBuilder.baseUrl("http://MANUFACTURE").build();

        return webClient.delete()
                .uri("/api/v1/products/{product_id}", productId)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
                    } else {
                        return response.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR,
                                        UNKNOWN_ERROR_MESSAGE,
                                        HttpStatus.INTERNAL_SERVER_ERROR
                                ))
                                .map(error -> ResponseEntity.status(response.statusCode()).body(error));
                    }
                });
    }
}
