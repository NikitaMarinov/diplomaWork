package com.diploma.resource;

import com.diploma.exception.LocationNotFoundException;
import com.diploma.model.Location;
import com.diploma.model.dto.LocationDto;
import com.diploma.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/location")
public class LocationResource {

    @Autowired
    private LogisticsService logisticsService;

    @PostMapping
    public Location createLocation(@RequestBody LocationDto location) throws LocationNotFoundException {
        return logisticsService.addNewLocation(location);
    }
}