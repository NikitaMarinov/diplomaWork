package com.diploma.service.kafka;

import com.diploma.avro.ManufactureListWrapper;
import com.diploma.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ManufactureConsumer {

    @Autowired
    private LogisticsService logisticsService;

    @KafkaListener(topics = "${kafka.manufacture-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenManufacture(ManufactureListWrapper manufactureListWrapper) {
        logisticsService.changeStatusToDelivery(manufactureListWrapper.getManufactures());
    }

}



