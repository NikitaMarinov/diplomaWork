package com.diploma.service.kafka;

import com.diploma.avro.OrderListWrapper;
import com.diploma.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

;

@Component
public class SalesConsumer {

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "${kafka.sales-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrder(OrderListWrapper orderListWrapper) {
        orderService.updateSalesStatus(orderListWrapper.getOrders());
    }

}
