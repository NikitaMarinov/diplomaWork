package com.diploma.service.kafka;

import com.diploma.avro.OrderListWrapper;
import com.diploma.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LogisticsConsumer {
    @Autowired
    private ManufactureService manufactureService;

    @KafkaListener(topics = "${kafka.logistics-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrder(OrderListWrapper orderListWrapper) {
        manufactureService.sentToSales(orderListWrapper.getOrders());
    }
}
