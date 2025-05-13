package com.diploma.service;

import com.diploma.exception.LocationNotFoundException;
import com.diploma.mapper.Mapper;
import com.diploma.model.Location;
import com.diploma.model.dto.LocationDto;
import com.diploma.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class LocationService {
    @Value("${application.warehouse}")
    private String WAREHOUSE;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private GeoService geoService;

    @Autowired
    private Mapper mapper;


    @Transactional
    public Location addNewLocation(LocationDto locationDto) throws LocationNotFoundException {
        Map<String, Double> cityLoc = geoService.getCityCoordinates(locationDto.getCity());
        Map<String, Double> warLoc = geoService.getCityCoordinates(WAREHOUSE);

        double latitude = warLoc.get("lat");
        double longitude = warLoc.get("lon");

        String coords = latitude + "," + longitude;

        double distance = geoService.distanceBetweenCities(warLoc.get("lat"), warLoc.get("lon"), cityLoc.get("lat"), cityLoc.get("lon"));

        Location location = mapper.toEntity(locationDto);

        location.setDistanceToWarehouse((int) distance);
        location.setLocation(coords);

        return locationRepository.save(location);
    }
}
