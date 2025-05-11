package com.diploma.service;

import com.diploma.avro.LogisticsDTO;
import com.diploma.avro.ManufactureDTO;
import com.diploma.avro.OrderDTO;
import com.diploma.avro.SalesDTO;
import com.diploma.constants.OrderStatus;
import com.diploma.mapper.Mapper;
import com.diploma.model.Order;
import com.diploma.repository.OrderRepository;
import com.diploma.service.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    Mapper mapper;

    @Transactional
    void sendOrdersAndUpdateStatus() {
        List<Order> orderList = orderRepository.findOrdersByStatus(OrderStatus.OPEN);
        List<OrderDTO> orderDTOList = mapper.toDtoList(orderList);

        List<Long> ordersId = orderList.stream().map(Order::getId).toList();

        orderProducer.sendOrder(orderDTOList);

        orderRepository.updateStatusByIds(OrderStatus.IN_PRODUCTION, ordersId);
    }

    @Transactional
    public void changeStatusToDelivery(List<ManufactureDTO> manufactureDTOS) {
        List<Long> ordersId = manufactureDTOS.stream().map(ManufactureDTO::getId).toList();

        orderRepository.updateStatusByIds(OrderStatus.DELIVERY, ordersId);
    }

    @Transactional
    public void sentToSales(List<LogisticsDTO> logisticsDTOS) {
        List<Long> ordersId = logisticsDTOS.stream().map(LogisticsDTO::getId).toList();

        orderRepository.updateStatusByIds(OrderStatus.DELIVERED, ordersId);
    }

    @Transactional
    public void updateSalesStatus(List<SalesDTO> salesDTOS) {
        List<Long> ordersId = salesDTOS.stream().map(SalesDTO::getId).toList();

        if (!salesDTOS.isEmpty()) {
            orderRepository.updateStatusByIds(OrderStatus.valueOf(salesDTOS.get(0).getStatus().name()), ordersId);
        }

    }
}
