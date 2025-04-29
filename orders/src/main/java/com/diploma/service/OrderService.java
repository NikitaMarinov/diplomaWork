package com.diploma.service;

import com.diploma.avro.OrderDTO;
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
    Mapper orderMapper;

    @Transactional
    void sendOrdersAndUpdateStatus(){
        List<Order> orderList = orderRepository.findOrdersByStatus(OrderStatus.OPEN);
        List<OrderDTO> orderDTOList = orderMapper.toDtoList(orderList);

        List<Long> ordersId = orderList.stream().map(Order::getId).toList();

        orderProducer.sendOrder(orderDTOList);

        orderRepository.updateStatusByIds(OrderStatus.IN_PRODUCTION, ordersId);
    }
}
