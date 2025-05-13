package com.diploma.resource;

import com.diploma.model.Location;
import com.diploma.model.dto.LocationDto;
import com.diploma.model.exception.LocationServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.diploma.model.constants.ErrorConstants.CLIENT_ERROR;
import static com.diploma.model.constants.ErrorConstants.SERVER_ERROR;
@RestController
@RequestMapping("/api/v1/external")
public class ExternalRequestController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping("/create-location")
    public Mono<ResponseEntity<Location>> createLocation(@RequestBody LocationDto locationDto) {
        WebClient webClient = webClientBuilder.baseUrl("http://LOGISTICS").build();

        return webClient.post()
                .uri("/api/v1/location")
                .bodyValue(locationDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new LocationServiceException(
                                CLIENT_ERROR,
                                "Client error: The city with name " + locationDto.getCity() + " - " + clientResponse.statusCode(),
                                HttpStatus.NOT_FOUND)))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new LocationServiceException(
                                SERVER_ERROR,
                                "Server error while creating a new destination point: " + clientResponse.statusCode(),
                                HttpStatus.INTERNAL_SERVER_ERROR)))
                .bodyToMono(Location.class)
                .map(location -> ResponseEntity.status(HttpStatus.CREATED).body(location))
                .doOnError(e -> {
                    System.err.println("An error occurred: " + e.getMessage());
                });
    }
}
