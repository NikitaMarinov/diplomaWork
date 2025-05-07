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

    //NewTopic topic = new NewTopic("name", 3, (short)1);  TODO
   // создаем топик с 3 партициями
    //создаем 3 реплики
    //3брокера
    private final KafkaTemplate<String, OrderListWrapper> kafkaTemplate;

    public LogisticsProducer(KafkaTemplate<String, OrderListWrapper> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLogistics(List<OrderDTO> orders) {
        OrderListWrapper wrapper = OrderListWrapper.newBuilder()
                .setOrders(orders)
                .build();

        kafkaTemplate.send(TOPIC, wrapper);
        System.out.println(wrapper.getOrders().get(0));
    }
}
