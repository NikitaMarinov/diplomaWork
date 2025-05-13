package com.diploma.service;

import com.diploma.exception.InvalidTransportDataException;
import com.diploma.exception.TransportNotFoundException;
import com.diploma.mapper.Mapper;
import com.diploma.model.Transport;
import com.diploma.model.dto.TransportDto;
import com.diploma.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class TransportService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private TransportRepository transportRepository;


    public Transport addNewTransport(TransportDto transportDto) {
        validateTransport(transportDto);

        Transport transportForSave = mapper.toEntity(transportDto);
        return transportRepository.save(transportForSave);
    }


    private void validateTransport(TransportDto transport) {
        if(transport.getCarType() == null || transport.getCarType().isBlank()){
            throw new InvalidTransportDataException("The car type can't be empty");
        }
        if (transport.getSpeed() == null || transport.getSpeed() < 20 || transport.getSpeed() > 200 ) {
            throw new InvalidTransportDataException("The speed should be between 20 and 200");
        }
        if (transport.getLoadVolume() == null || transport.getLoadVolume() <= 200 || transport.getLoadVolume() > 1000000 ) {
            throw new InvalidTransportDataException("The load volume should be between 200 and 1000000");
        }

    }

    public void deleteTransport(Long transportId) throws TransportNotFoundException {
        Optional<Transport> transport = transportRepository.findById(transportId);
        if (transport.isEmpty()) {
            throw new TransportNotFoundException("The transport with id " + transportId + " does not exist");
        }

        transportRepository.deleteById(transportId);
    }
}
