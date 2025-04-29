package com.diploma.service.kafka;

import com.diploma.avro.OrderDTO;
import com.diploma.avro.OrderListWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProducer {
    @Value("${kafka.order-topic}")
    private String TOPIC;

    private final KafkaTemplate<String, OrderListWrapper> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderListWrapper> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(List<OrderDTO> orders) {
        OrderListWrapper wrapper = OrderListWrapper.newBuilder()
                .setOrders(orders)
                .build();

        kafkaTemplate.send(TOPIC, wrapper);
        System.out.println("Sent to Kafka: " + wrapper);
    }
}
