package com.diploma.service.kafka;

import com.diploma.avro.LogisticsListWrapper;
import com.diploma.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LogisticsConsumer {
    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "${kafka.logistics-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrder(LogisticsListWrapper logisticsListWrapper) {
        orderService.sentToSales(logisticsListWrapper.getLogistics());
    }
}
