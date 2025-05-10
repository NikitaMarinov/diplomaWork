package com.diploma.service.kafka;

import com.diploma.avro.OrderListWrapper;
import com.diploma.service.DataProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @Autowired
    private DataProcService dataProcService;

    @KafkaListener(topics = "${kafka.order-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrder(OrderListWrapper orderListWrapper) {
        dataProcService.saveOders(orderListWrapper.getOrders());
    }

}
