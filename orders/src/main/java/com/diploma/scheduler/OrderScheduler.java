package com.diploma.scheduler;

import com.diploma.service.OrderGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderScheduler {
    @Autowired
    private OrderGenerationService orderGenerationService;

    @Scheduled(fixedRateString = "${order.frequency.ms}")
    public void generateOrders() {
        System.out.println("Generating orders...");
        orderGenerationService.generateOrders();
    }
}
