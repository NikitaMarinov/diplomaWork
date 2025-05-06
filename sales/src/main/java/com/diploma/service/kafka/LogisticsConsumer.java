package com.diploma.service.kafka;

import com.diploma.avro.OrderListWrapper;
import com.diploma.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LogisticsConsumer {
    @Autowired
    private SalesService salesService;

    @KafkaListener(topics = "${kafka.logistics-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrder(OrderListWrapper orderListWrapper) {
        salesService.sentToSales(orderListWrapper.getOrders());
        System.out.println(orderListWrapper.getOrders().size());
    }
}
