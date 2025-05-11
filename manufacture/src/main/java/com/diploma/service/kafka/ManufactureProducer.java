package com.diploma.service.kafka;

import com.diploma.avro.ManufactureDto;
import com.diploma.avro.ManufactureListWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManufactureProducer {
    @Value("${kafka.manufacture-topic}")
    private String TOPIC;

    private final KafkaTemplate<String, ManufactureListWrapper> kafkaTemplate;

    public ManufactureProducer(KafkaTemplate<String, ManufactureListWrapper> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendManufacture(List<ManufactureDto> manufactureDtos) {
        ManufactureListWrapper wrapper = ManufactureListWrapper.newBuilder()
                .setManufactures(manufactureDtos)
                .build();

        kafkaTemplate.send(TOPIC, wrapper);
        System.out.println(wrapper.getManufactures().get(0));
    }
}
