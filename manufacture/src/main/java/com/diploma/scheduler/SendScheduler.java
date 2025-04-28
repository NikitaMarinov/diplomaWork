package com.diploma.scheduler;

import com.diploma.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendScheduler {
    @Autowired
    private SendService service;

    @Scheduled(fixedRateString = "${send.frequency.ms}")
    public void sendOrders() {
        System.out.println("Sending orders to logistics...");
        service.sendOrders();
    }
}
