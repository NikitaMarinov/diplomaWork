package com.diploma.resource;

import com.diploma.model.Location;
import com.diploma.model.Product;
import com.diploma.model.Transport;
import com.diploma.model.dto.LocationDto;
import com.diploma.exception.ErrorResponse;
import com.diploma.model.dto.ProductDto;
import com.diploma.model.dto.TransportDto;
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
@RequestMapping("/api/v1/external/logistics")
public class LogisticsRequestController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping("/create-location")
    public Mono<ResponseEntity<?>> createLocation(@RequestBody LocationDto locationDto) {
        WebClient webClient = webClientBuilder.baseUrl("http://LOGISTICS").build();

        return webClient.post()
                .uri("/api/v1/location")
                .bodyValue(locationDto)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(Location.class)
                                .map(location -> ResponseEntity.status(HttpStatus.CREATED).body(location));
                    } else {
                        return clientResponse.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR,
                                        UNKNOWN_ERROR_MESSAGE,
                                        HttpStatus.INTERNAL_SERVER_ERROR
                                ))
                                .map(error -> ResponseEntity
                                        .status(clientResponse.statusCode())
                                        .body(error));
                    }
                });
    }

    @DeleteMapping("/delete-transport/{transport_id}")
    public Mono<ResponseEntity<?>> deleteTransport(@PathVariable("transport_id") Long transportId) {
        WebClient webClient = webClientBuilder.baseUrl("http://LOGISTICS").build();

        return webClient.delete()
                .uri("/api/v1/transport/{transport_id}", transportId)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
                    } else {
                        return clientResponse.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR,
                                        UNKNOWN_ERROR_MESSAGE,
                                        HttpStatus.INTERNAL_SERVER_ERROR
                                ))
                                .map(error -> ResponseEntity
                                        .status(clientResponse.statusCode())
                                        .body(error));
                    }
                });
    }


    @PostMapping("/create-transport")
    public Mono<ResponseEntity<?>> createTransport(@RequestBody TransportDto transportDto) {
        WebClient webClient = webClientBuilder.baseUrl("http://LOGISTICS").build();

        return webClient.post()
                .uri("/api/v1/transport")
                .bodyValue(transportDto)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(Transport.class)
                                .map(transport -> ResponseEntity.status(HttpStatus.CREATED).body(transport));
                    } else {
                        return clientResponse.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR,
                                        UNKNOWN_ERROR_MESSAGE,
                                        HttpStatus.INTERNAL_SERVER_ERROR
                                ))
                                .map(error -> ResponseEntity
                                        .status(clientResponse.statusCode())
                                        .body(error));
                    }
                });
    }

    @DeleteMapping("/delete-location/{location_id}")
    public Mono<ResponseEntity<?>> deleteLocation(@PathVariable("location_id") Long locationId) {
        WebClient webClient = webClientBuilder.baseUrl("http://LOGISTICS").build();

        return webClient.delete()
                .uri("/api/v1/location/{location_id}", locationId)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
                    } else {
                        return clientResponse.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR,
                                        UNKNOWN_ERROR_MESSAGE,
                                        HttpStatus.INTERNAL_SERVER_ERROR
                                ))
                                .map(error -> ResponseEntity
                                        .status(clientResponse.statusCode())
                                        .body(error));
                    }
                });
    }

    @PostMapping("/create-product")
    public Mono<ResponseEntity<?>> createProduct(@RequestBody ProductDto productDto) {
        WebClient webClient = webClientBuilder.baseUrl("http://LOGISTICS").build();

        return webClient.post()
                .uri("/api/v1/products")
                .bodyValue(productDto)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(Product.class)
                                .map(product -> ResponseEntity.status(HttpStatus.CREATED).body(product));
                    } else {
                        return clientResponse.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR,
                                        UNKNOWN_ERROR_MESSAGE,
                                        HttpStatus.INTERNAL_SERVER_ERROR
                                ))
                                .map(error -> ResponseEntity
                                        .status(clientResponse.statusCode())
                                        .body(error));
                    }
                });
    }

    @DeleteMapping("/delete-product/{product_id}")
    public Mono<ResponseEntity<?>> deleteProduct(@PathVariable("product_id") Long productId) {
        WebClient webClient = webClientBuilder.baseUrl("http://LOGISTICS").build();

        return webClient.delete()
                .uri("/api/v1/products/{product_id}", productId)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
                    } else {
                        return clientResponse.bodyToMono(ErrorResponse.class)
                                .defaultIfEmpty(new ErrorResponse(
                                        UNKNOWN_ERROR,
                                        UNKNOWN_ERROR_MESSAGE,
                                        HttpStatus.INTERNAL_SERVER_ERROR
                                ))
                                .map(error -> ResponseEntity
                                        .status(clientResponse.statusCode())
                                        .body(error));
                    }
                });
    }


}
