package com.diploma.service.kafka;

import com.diploma.avro.LogisticsListWrapper;
import com.diploma.service.DataProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LogisticsConsumer {

    @Autowired
    private DataProcService dataProcService;

    @KafkaListener(topics = "${kafka.logistics-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrder(LogisticsListWrapper logisticsListWrapper) {
        dataProcService.saveLogistics(logisticsListWrapper.getLogistics());
    }

}
