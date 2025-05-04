package com.diploma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendService {
    @Autowired
    private LogisticsService logisticsService;

    public void sendOrders() {
        logisticsService.sendOrdersToSalesAndUpdateStatus();
    }
}
