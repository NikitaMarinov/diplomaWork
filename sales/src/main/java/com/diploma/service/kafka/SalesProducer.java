package com.diploma.service.kafka;

import com.diploma.avro.SalesDTO;
import com.diploma.avro.SalesListWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesProducer {
    @Value("${kafka.sales-topic}")
    private String TOPIC;

    private final KafkaTemplate<String, SalesListWrapper> kafkaTemplate;

    public SalesProducer(KafkaTemplate<String, SalesListWrapper> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSales(List<SalesDTO> salesDTOS) {
        SalesListWrapper wrapper = SalesListWrapper.newBuilder()
                .setSales(salesDTOS)
                .build();

        kafkaTemplate.send(TOPIC, wrapper);
    }
}
