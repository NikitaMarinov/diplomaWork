package com.diploma.service.kafka;

import com.diploma.avro.ManufactureListWrapper;
import com.diploma.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ManufactureConsumer {

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "${kafka.manufacture-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrder(ManufactureListWrapper manufactureListWrapper) {
       orderService.changeStatusToDelivery(manufactureListWrapper.getManufactures());
    }

}