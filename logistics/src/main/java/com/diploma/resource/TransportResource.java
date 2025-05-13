package com.diploma.resource;

import com.diploma.exception.TransportNotFoundException;
import com.diploma.model.Transport;
import com.diploma.model.dto.TransportDto;
import com.diploma.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transport")
public class TransportResource {
    @Autowired
    private TransportService transportService;

    @PostMapping
    public Transport createTransport(@RequestBody TransportDto transportDto) {
        return transportService.addNewTransport(transportDto);
    }

    @DeleteMapping("/{location_id}")
    public void deleteLocation(@PathVariable("location_id") Long transportId) throws TransportNotFoundException {
        transportService.deleteTransport(transportId);
    }
}
