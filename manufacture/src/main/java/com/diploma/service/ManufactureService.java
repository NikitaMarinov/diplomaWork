package com.diploma.service;

import com.diploma.avro.OrderDTO;
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

import java.time.LocalDateTime;
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
            order.setProductionEndTime(LocalDateTime.now().plusSeconds(((long) manufacturingTimePerObject * order.getQuantity()) / 10000)); // TODO ПОТОМУ УБРАТЬ ОДИН НОЛЬ!!!!!
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
        List<OrderDTO> ordersDtoList = mapper.toDtoList(orders);

        manufactureProducer.sendManufacture(ordersDtoList);
    }

    @Transactional
    public void sentToSales(List<OrderDTO> orders) {
        List<Long> ordersIds = orders.stream().map(OrderDTO::getId).toList();

        orderRepository.updateStatusByMigrationIds(DELIVERED, ordersIds);
    }

    @Transactional
    public void updateSalesStatus(List<OrderDTO> orders) {
        List<Long> ids = orders.stream().map(OrderDTO::getId).toList();

        if(!orders.isEmpty()) {
            orderRepository.updateStatusByMigrationIds(OrderStatus.valueOf(orders.get(0).getStatus().name()), ids);
        }
    }
}
