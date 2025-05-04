package com.diploma.service.kafka;


import com.diploma.avro.OrderDTO;
import com.diploma.avro.OrderListWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogisticsProducer {
    @Value("${kafka.logistics-topic}")
    private String TOPIC;

    private final KafkaTemplate<String, OrderListWrapper> kafkaTemplate;

    public LogisticsProducer(KafkaTemplate<String, OrderListWrapper> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLogistics(List<OrderDTO> orders) {
        OrderListWrapper wrapper = OrderListWrapper.newBuilder()
                .setOrders(orders)
                .build();

        kafkaTemplate.send(TOPIC, wrapper);
    }
}
