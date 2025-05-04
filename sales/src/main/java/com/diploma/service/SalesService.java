package com.diploma.service;

import com.diploma.avro.OrderDTO;
import com.diploma.mapper.Mapper;
import com.diploma.model.Order;
import com.diploma.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Mapper mapper;

    public void acceptToSale(List<OrderDTO> orders) {
        List<Order> orderList = mapper.toEntityList(orders);

        for (Order order : orderList) {
            order.setId(null);
        }

        orderRepository.saveAll(orderList);
    }
}
