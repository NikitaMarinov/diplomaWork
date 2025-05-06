package com.diploma.service;

import com.diploma.avro.OrderDTO;
import com.diploma.constants.OrderStatus;
import com.diploma.mapper.Mapper;
import com.diploma.model.Order;
import com.diploma.repository.OrderRepository;
import com.diploma.service.kafka.SalesProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SalesService {
    private final Random random = new Random();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private SalesProducer salesProducer;

    @Transactional
    public void acceptToSale(List<OrderDTO> orders) {
        List<Order> orderList = mapper.toEntityList(orders);

        for (Order order : orderList) {
            order.setId(null);
            order.setStatus(OrderStatus.IN_PRODUCTION);
        }

        orderRepository.saveAll(orderList);
    }

    @Transactional
    public void changeStatusToDelivery(List<OrderDTO> orders) {
        List<Long> ids = orders.stream().map(OrderDTO::getId).toList();

        orderRepository.updateStatusByMigrationIds(OrderStatus.DELIVERY, ids);
    }

    @Transactional
    public void sentToSales(List<OrderDTO> orders) {
        List<Long> ids = orders.stream().map(OrderDTO::getId).toList();
        System.out.println(orders.get(0).toString());
        orderRepository.updateStatusByMigrationIds(OrderStatus.DELIVERED, ids);
    }

    @Transactional
    public void sendSales() {
        List<Order> orders = orderRepository.findOrdersByStatus(OrderStatus.DELIVERED);

        for (Order order : orders) {
            int chance = random.nextInt(100);

            if (chance < 90) {
                order.setStatus(OrderStatus.SOLD);
            } else {
                order.setStatus(OrderStatus.RETURNED);
            }
        }

        List<Order> soldOrders = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.SOLD)
                .collect(Collectors.toList());

        List<Order> returnedOrders = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.RETURNED)
                .collect(Collectors.toList());

        List<Long> soldMigrationIds = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.SOLD)
                .map(Order::getMigrationId)
                .collect(Collectors.toList());

        List<Long> returnedMigrationIds = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.RETURNED)
                .map(Order::getMigrationId)
                .collect(Collectors.toList());

        if (!soldMigrationIds.isEmpty()) {
            orderRepository.updateStatusByMigrationIds(OrderStatus.SOLD, soldMigrationIds);
        }
        if (!returnedMigrationIds.isEmpty()) {
            orderRepository.updateStatusByMigrationIds(OrderStatus.RETURNED, returnedMigrationIds);
        }

        if (!soldOrders.isEmpty()) {
            salesProducer.sendSales(mapper.toDtoList(soldOrders));
        }
        if (!returnedOrders.isEmpty()) {
            salesProducer.sendSales(mapper.toDtoList(returnedOrders));
        }    }
}
