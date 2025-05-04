package com.diploma.service.kafka;

import com.diploma.avro.OrderDTO;
import com.diploma.avro.OrderListWrapper;
import com.diploma.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManufactureProducer {
    @Value("${kafka.manufacture-topic}")
    private String TOPIC;

    private final KafkaTemplate<String, OrderListWrapper> kafkaTemplate;

    public ManufactureProducer(KafkaTemplate<String, OrderListWrapper> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendManufacture(List<OrderDTO> orders) {
        OrderListWrapper wrapper = OrderListWrapper.newBuilder()
                .setOrders(orders)
                .build();

        kafkaTemplate.send(TOPIC, wrapper);
    }
}
