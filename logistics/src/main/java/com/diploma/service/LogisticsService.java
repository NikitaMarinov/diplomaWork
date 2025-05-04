package com.diploma.service;

import com.diploma.avro.OrderDTO;
import com.diploma.constants.OrderStatus;
import com.diploma.exception.LocationNotFoundException;
import com.diploma.mapper.Mapper;
import com.diploma.model.Location;
import com.diploma.model.Order;
import com.diploma.model.Transport;
import com.diploma.model.dto.LocationDto;
import com.diploma.repository.LocationRepository;
import com.diploma.repository.OrderRepository;
import com.diploma.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.diploma.constants.OrderStatus.DELIVERY;

@Service
public class LogisticsService {

    @Value("${application.warehouse}")
    private String WAREHOUSE;

    @Autowired
    private GeoService geoService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    Mapper mapper;

    @Transactional
    public void applicationAcceptance(List<OrderDTO> orders) {
        List<Order> ordersList = mapper.toEntityList(orders);
        List<Location> locationList = locationRepository.findAll();

        List<Transport> transports = transportRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(Transport::getLoadVolume))
                .toList();

        for (Order order : ordersList) {
            Transport suitableTransport = transports.stream()
                    .filter(t -> t.getLoadVolume() >= order.getQuantity())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No suitable transport for order " + order.getId()));
            order.setTransport(suitableTransport);
        }
        for (Order order : ordersList) {
            Integer distanceToWarehouse = locationList.get((int) (order.getLocation().getId() - 1)).getDistanceToWarehouse();
            String deliveryDuration = String.valueOf(distanceToWarehouse / order.getTransport().getSpeed());
            order.setDeliveryTime(LocalDateTime.now().plusSeconds(Long.parseLong(deliveryDuration))); // TODO ПОТОМУ УБРАТЬ ОДИН НОЛЬ!!!!!
            order.setDeliveryDuration(deliveryDuration);
            order.setMigrationId(order.getId());
            order.setStatus(OrderStatus.IN_PRODUCTION);
            order.setId(null);
        }

        System.out.println("SAVE ORDERS----"+ ordersList.get(0).getMigrationId());
        orderRepository.saveAll(ordersList);
    }

    @Transactional
    public Location addNewLocation(LocationDto locationDto) throws LocationNotFoundException {
        Map<String, Double> cityLoc = geoService.getCityCoordinates(locationDto.getCity());
        Map<String,Double> warLoc = geoService.getCityCoordinates(WAREHOUSE);

        double distance = geoService.distanceBetweenCities(warLoc.get("lat"), warLoc.get("lon"), cityLoc.get("lat"), cityLoc.get("lon"));

        Location location = mapper.toEntity(locationDto);

        location.setDistanceToWarehouse((int) distance);

        return locationRepository.save(location);
    }

    @Transactional
    public void changeStatusToDelivery(List<OrderDTO> orders) {
        List<Long> ordersId = orders.stream().map(OrderDTO::getId).toList();

        orderRepository.updateStatusByIds(OrderStatus.DELIVERY, ordersId);
    }


}
