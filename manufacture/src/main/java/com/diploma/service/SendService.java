package com.diploma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendService {
    @Autowired
    private ManufactureService manufactureService;

    public void sendOrders() {
        manufactureService.sendOrdersToLogisticAndUpdateStatus();
    }
}
