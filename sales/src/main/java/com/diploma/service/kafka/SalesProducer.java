package com.diploma.service.kafka;

import com.diploma.avro.OrderDTO;
import com.diploma.avro.OrderListWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesProducer {
    @Value("${kafka.sales-topic}")
    private String TOPIC;

    private final KafkaTemplate<String, OrderListWrapper> kafkaTemplate;

    public SalesProducer(KafkaTemplate<String, OrderListWrapper> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSales(List<OrderDTO> orders) {
        OrderListWrapper wrapper = OrderListWrapper.newBuilder()
                .setOrders(orders)
                .build();

        kafkaTemplate.send(TOPIC, wrapper);
        System.out.println(wrapper.getOrders().get(0));
    }
}
