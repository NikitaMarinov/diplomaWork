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
import com.diploma.service.kafka.LogisticsProducer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.diploma.constants.OrderStatus.DELIVERED;
import static com.diploma.constants.OrderStatus.DELIVERY;

@Service
public class LogisticsService {

    @Value("${application.warehouse}")
    private String WAREHOUSE;

    @Value("${batch.size}")
    private int batchSize;

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

    @Autowired
    private LogisticsProducer logisticsProducer;


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void applicationAcceptance(List<OrderDTO> orders) {
        List<Order> ordersList = mapper.toEntityList(orders);

        for (Order order : ordersList) {
            order.setMigrationId(order.getId());
            order.setStatus(OrderStatus.IN_PRODUCTION);
            order.setId(null);
            order.setTransport(null);
        }

        orderRepository.saveAll(ordersList);
    }

    @Transactional
    public Location addNewLocation(LocationDto locationDto) throws LocationNotFoundException {
        Map<String, Double> cityLoc = geoService.getCityCoordinates(locationDto.getCity());
        Map<String, Double> warLoc = geoService.getCityCoordinates(WAREHOUSE);

        double distance = geoService.distanceBetweenCities(warLoc.get("lat"), warLoc.get("lon"), cityLoc.get("lat"), cityLoc.get("lon"));

        Location location = mapper.toEntity(locationDto);

        location.setDistanceToWarehouse((int) distance);

        return locationRepository.save(location);
    }

    @Transactional
    public void changeStatusToDelivery(List<OrderDTO> orders) {
        List<Order> ordersList = mapper.toEntityList(orders);
        List<Location> locationList = locationRepository.findAll();

        List<Transport> transports = transportRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(Transport::getLoadVolume))
                .toList();
        Map<Long, Location> locationMap = locationList.stream()
                .collect(Collectors.toMap(Location::getId, location -> location));

        for (Order order : ordersList) {
            Transport suitableTransport = transports.stream()
                    .filter(t -> t.getLoadVolume() >= order.getQuantity())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No suitable transport for order " + order.getId()));
            order.setTransport(suitableTransport);
            order.setLocation(locationMap.get(order.getLocation().getId()));
        }

        for (Order order : ordersList) {
            Integer distanceToWarehouse = locationMap.get(order.getLocation().getId()).getDistanceToWarehouse();
            String deliveryDuration = String.valueOf(distanceToWarehouse / order.getTransport().getSpeed());

            order.setDeliveryTime(LocalDateTime.now().plusSeconds(Long.parseLong(deliveryDuration)));
            order.setDeliveryDuration(deliveryDuration);
            order.setStatus(DELIVERY);
        }

        updateAllByMigrationId(ordersList);
    }

    @Transactional
    public void updateAllByMigrationId(List<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);

            String jpql = "UPDATE Order o SET " +
                    "o.status = :status, " +
                    "o.deliveryDuration = :deliveryDuration, " +
                    "o.deliveryTime = :deliveryTime, " +
                    "o.transport = :transport, " +
                    "o.location = :location " +
                    "WHERE o.migrationId = :migrationId";

            Query query = entityManager.createQuery(jpql);
            query.setParameter("status", order.getStatus());
            query.setParameter("deliveryDuration", order.getDeliveryDuration());
            query.setParameter("deliveryTime", order.getDeliveryTime());
            query.setParameter("transport", order.getTransport());
            query.setParameter("location", order.getLocation());
            query.setParameter("migrationId", order.getMigrationId());
            query.executeUpdate();
            if (i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }


    @Transactional
    public void sendOrdersToSalesAndUpdateStatus() {
        List<Long> expiredOrders = orderRepository.findExpiredOrderIds();
        orderRepository.updateStatusByIds(DELIVERED, expiredOrders);
        List<Order> orders = orderRepository.findOrdersByIds(expiredOrders);

        List<OrderDTO> ordersDtoList = mapper.toDtoList(orders);

        System.out.println(ordersDtoList.get(0));
        logisticsProducer.sendLogistics(ordersDtoList);
    }

    @Transactional
    public void updateSalesStatus(List<OrderDTO> orders){
        List<Long> ids = orders.stream().map(OrderDTO::getId).toList();

        if(!orders.isEmpty()) {
            orderRepository.updateStatusByMigrationIds(OrderStatus.valueOf(orders.get(0).getStatus().name()), ids);
        }
    }

}
