package com.diploma.service.kafka;

import com.diploma.avro.SalesListWrapper;
import com.diploma.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class SalesConsumer {

    @Autowired
    private LogisticsService logisticsService;

    @KafkaListener(topics = "${kafka.sales-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrder(SalesListWrapper salesListWrapper) {
        logisticsService.updateSalesStatus(salesListWrapper.getSales());
    }

}
