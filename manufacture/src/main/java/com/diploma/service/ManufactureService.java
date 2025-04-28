package com.diploma.service;

import com.diploma.constants.OrderStatus;
import com.diploma.mapper.Mapper;
import com.diploma.model.Manufacture;
import com.diploma.model.Order;
import com.diploma.avro.OrderDTO;
import com.diploma.repository.ManufactureRepository;
import com.diploma.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ManufactureService {

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Mapper mapper;

    @Transactional
    public void sentToManufacture(List<OrderDTO> ordersDtoList) {
        List<Order> orderList = mapper.toEntityList(ordersDtoList);

        Map<Long, Manufacture> manufactureMap = manufactureRepository.findAll()
                .stream()
                .collect(Collectors.toMap(manufacture -> manufacture.getProduct().getId(), manufacture -> manufacture));
        Integer manufacturingTimePerObject;

        for (Order order : orderList) {
            manufacturingTimePerObject = Integer.valueOf(manufactureMap.get(order.getProduct().getId()).getManufacturing_time());
            order.setStatus(OrderStatus.IN_PRODUCTION);
            order.setProduction_end_time(LocalDateTime.now().plusSeconds((long) manufacturingTimePerObject * order.getQuantity()));
            order.setId(null);
        }

        orderRepository.saveAll(orderList);
    }

    public void sendOrdersToLogisticAndUpdateStatus() {

    }
}
