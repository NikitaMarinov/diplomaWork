package com.diploma.resource;

import com.diploma.exception.ErrorResponse;
import com.diploma.model.Location;
import com.diploma.model.Product;
import com.diploma.model.dto.LocationDto;
import com.diploma.model.dto.ProductDto;
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

import static com.diploma.constants.ErrorConstants.UNKNOWN_ERROR;
import static com.diploma.constants.ErrorConstants.UNKNOWN_ERROR_MESSAGE;

@RestController
@RequestMapping("/api/v1/external/sales")
public class SalesRequestController {

    @Autowired
    private WebClient.Builder webClientBuilder;


    @PostMapping("/create-location")
    public Mono<ResponseEntity<?>> createLocation(@RequestBody LocationDto locationDto) {
        WebClient webClient = webClientBuilder.baseUrl("http://SALES").build();

        return webClient.post()
                .uri("/api/v1/location")
                .bodyValue(locationDto)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(Location.class)
                                .map(location -> ResponseEntity.status(HttpStatus.CREATED).body(location));
                    } else {
                        return response.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR, UNKNOWN_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR))
                                .map(error -> ResponseEntity.status(response.statusCode()).body(error));
                    }
                });
    }

    @DeleteMapping("/delete-location/{location_id}")
    public Mono<ResponseEntity<?>> deleteLocation(@PathVariable("location_id") Long locationId) {
        WebClient webClient = webClientBuilder.baseUrl("http://SALES").build();

        return webClient.delete()
                .uri("/api/v1/location/{location_id}", locationId)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return Mono.just(ResponseEntity.noContent().build());
                    } else {
                        return response.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR, UNKNOWN_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR))
                                .map(error -> ResponseEntity.status(response.statusCode()).body(error));
                    }
                });
    }


    @PostMapping("/create-product")
    public Mono<ResponseEntity<?>> createProduct(@RequestBody ProductDto productDto) {
        WebClient webClient = webClientBuilder.baseUrl("http://SALES").build();

        return webClient.post()
                .uri("/api/v1/products")
                .bodyValue(productDto)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(Product.class)
                                .map(product -> ResponseEntity.status(HttpStatus.CREATED).body(product));
                    } else {
                        return response.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR, UNKNOWN_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR))
                                .map(error -> ResponseEntity.status(response.statusCode()).body(error));
                    }
                });
    }

    @DeleteMapping("/delete-product/{product_id}")
    public Mono<ResponseEntity<?>> deleteProduct(@PathVariable("product_id") Long productId) {
        WebClient webClient = webClientBuilder.baseUrl("http://SALES").build();

        return webClient.delete()
                .uri("/api/v1/products/{product_id}", productId)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return Mono.just(ResponseEntity.noContent().build());
                    } else {
                        return response.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR, UNKNOWN_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR))
                                .map(error -> ResponseEntity.status(response.statusCode()).body(error));
                    }
                });
    }
}