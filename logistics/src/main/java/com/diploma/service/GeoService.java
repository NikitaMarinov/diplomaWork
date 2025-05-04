package com.diploma.service;

import com.diploma.exception.LocationNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class GeoService {
    private static final double EARTH_RADIUS = 6371.0;

    private final WebClient webClient = WebClient.create();

    public Map<String,Double> getCityCoordinates(String city) throws LocationNotFoundException {
        if (city == null || city.isEmpty()) {
            throw new LocationNotFoundException("City name is null or empty!");
        }

        Map<String,Double> coords = new HashMap<>();

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("nominatim.openstreetmap.org")
                .path("/search")
                .queryParam("q", city)
                .queryParam("format", "json")
                .queryParam("limit", 1)
                .build()
                .toUri();

        String response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode responseArray;

        try {
            responseArray = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new LocationNotFoundException("Failed to parse location service response");
        }

        if (responseArray.isArray() && !responseArray.isEmpty()) {
            JsonNode place = responseArray.get(0);

            double latitude = place.path("lat").asDouble();
            double longitude = place.path("lon").asDouble();

            coords.put("lat", latitude);
            coords.put("lon", longitude);

            return coords;
        } else {
            throw new LocationNotFoundException("Location of the city '" + city + "' not found.");
        }
    }

    public double distanceBetweenCities(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

}
