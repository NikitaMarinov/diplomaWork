package com.diploma.service;

import com.diploma.avro.LogisticsDto;
import com.diploma.avro.ManufactureDto;
import com.diploma.avro.OrderDTO;
import com.diploma.avro.SalesDto;
import com.diploma.constants.OrderStatus;
import com.diploma.mapper.Mapper;
import com.diploma.model.Manufacture;
import com.diploma.model.Order;
import com.diploma.repository.ManufactureRepository;
import com.diploma.repository.OrderRepository;
import com.diploma.service.kafka.ManufactureProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.diploma.constants.OrderStatus.DELIVERED;
import static com.diploma.constants.OrderStatus.DELIVERY;

@Service
public class ManufactureService {

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    ManufactureProducer manufactureProducer;

    @Transactional
    public void sentToManufacture(List<OrderDTO> ordersDtoList) {
        List<Order> orderList = mapper.toEntityList(ordersDtoList);

        Map<Long, Manufacture> manufactureMap = manufactureRepository.findAll()
                .stream()
                .collect(Collectors.toMap(manufacture -> manufacture.getProduct().getId(), manufacture -> manufacture));
        int manufacturingTimePerObject;

        for (Order order : orderList) {
            manufacturingTimePerObject = Integer.parseInt(manufactureMap.get(order.getProduct().getId()).getManufacturing_time());
            order.setStatus(OrderStatus.IN_PRODUCTION);
            order.setProductionEndTime(ZonedDateTime.now(ZoneId.of("UTC")).plusSeconds(((long) manufacturingTimePerObject * order.getQuantity()) / 10000)); // TODO ПОТОМУ УБРАТЬ ОДИН НОЛЬ!!!!!
            order.setProductionTime(String.valueOf((long) manufacturingTimePerObject * order.getQuantity()));
            order.setId(null);
        }

        orderRepository.saveAll(orderList);
    }

    @Transactional
    public void sendOrdersToLogisticAndUpdateStatus() {
        List<Long> expiredOrders = orderRepository.findExpiredOrderIds();
        orderRepository.updateStatusByIds(DELIVERY, expiredOrders);
        List<Order> orders = orderRepository.findOrdersByIds(expiredOrders);
        List<ManufactureDto> manufactureDtos = mapper.toManufactureDtoList(orders);

        manufactureProducer.sendManufacture(manufactureDtos);
    }

    @Transactional
    public void sentToSales(List<LogisticsDto> logisticsDtos) {
        List<Long> ordersIds = logisticsDtos.stream().map(LogisticsDto::getId).toList();

        orderRepository.updateStatusByMigrationIds(DELIVERED, ordersIds);
    }

    @Transactional
    public void updateSalesStatus(List<SalesDto> salesDtos) {
        List<Long> ids = salesDtos.stream().map(SalesDto::getId).toList();

        if (!ids.isEmpty()){
            orderRepository.updateStatusByIds(OrderStatus.valueOf(salesDtos.get(0).getStatus().name()), ids);
        }
    }
}
