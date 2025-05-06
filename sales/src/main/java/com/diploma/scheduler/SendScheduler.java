package com.diploma.scheduler;

import com.diploma.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendScheduler {
    @Autowired
    private SalesService salesService;

    @Scheduled(fixedRateString = "${send.frequency.ms}")
    public void sendOrders() {
        salesService.sendSales();
    }
}
