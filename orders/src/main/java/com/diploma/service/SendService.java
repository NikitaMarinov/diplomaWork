package com.diploma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendService {
    @Autowired
    private OrderService orderService;

    public void sendOrders() {
        orderService.sendOrdersAndUpdateStatus();
    }
}
