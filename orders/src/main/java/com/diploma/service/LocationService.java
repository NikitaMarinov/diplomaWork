package com.diploma.service;

import com.diploma.exception.InvalidLocationDataException;
import com.diploma.exception.LocationNotFoundException;
import com.diploma.mapper.Mapper;
import com.diploma.model.Location;
import com.diploma.model.dto.LocationDto;
import com.diploma.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private Mapper mapper;


    @Transactional
    public Location addNewLocation(LocationDto locationDto) throws LocationNotFoundException {
        Location location = mapper.toEntity(locationDto);
        validateLocation(location);

        return locationRepository.save(location);
    }

    private void validateLocation(Location location) {
        if(location.getCity() == null || location.getCity().isEmpty()) {
            throw new InvalidLocationDataException("Location city is empty");
        }
        if (location.getCountry() == null || location.getCountry().isEmpty()) {
            throw new InvalidLocationDataException("Location country is empty");
        }
    }

    public void deleteLocation(Long locationId) throws LocationNotFoundException {
        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isEmpty()) {
            throw new LocationNotFoundException("Location with id: " + locationId + " not found!");
        }
        locationRepository.delete(location.get());
    }
}
