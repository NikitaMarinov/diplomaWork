package com.diploma.service.kafka;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManufactureProducer {
    @Value("${kafka.manufacture-topic}")
    private String TOPIC;

//    private final KafkaTemplate<String, OrderListWrapper> kafkaTemplate;
//
//    public ManufactureProducer(KafkaTemplate<String, OrderListWrapper> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendManufacture(List<OrderDTO> orders) {
//        OrderListWrapper wrapper = OrderListWrapper.newBuilder()
//                .setOrders(orders)
//                .build();
//
//        kafkaTemplate.send(TOPIC, wrapper);
//        System.out.println("Sent to Kafka Manufacture: " + wrapper);
//    }
}
