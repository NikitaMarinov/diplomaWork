package com.diploma.service.kafka;


import com.diploma.avro.LogisticsDTO;
import com.diploma.avro.LogisticsListWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogisticsProducer {
    @Value("${kafka.logistics-topic}")
    private String TOPIC;

    private final KafkaTemplate<String, LogisticsListWrapper> kafkaTemplate;

    public LogisticsProducer(KafkaTemplate<String, LogisticsListWrapper> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLogistics(List<LogisticsDTO> logisticsDTOList) {
        LogisticsListWrapper wrapper = LogisticsListWrapper.newBuilder()
                .setLogistics(logisticsDTOList)
                .build();
        kafkaTemplate.send(TOPIC, wrapper);
    }
}
