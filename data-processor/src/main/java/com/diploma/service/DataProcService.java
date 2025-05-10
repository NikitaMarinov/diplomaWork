package com.diploma.service;

import com.diploma.avro.LogisticsDTO;
import com.diploma.avro.ManufactureDTO;
import com.diploma.avro.OrderDTO;
import com.diploma.avro.SalesDTO;
import com.diploma.mapper.Mapper;
import com.diploma.model.Logistics;
import com.diploma.model.Manufacture;
import com.diploma.model.Order;
import com.diploma.model.Sales;
import com.diploma.repository.LogisticsRepository;
import com.diploma.repository.ManufactureRepository;
import com.diploma.repository.OrderRepository;
import com.diploma.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataProcService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ManufactureRepository manufactureRepository;
    @Autowired
    SalesRepository salesRepository;
    @Autowired
    LogisticsRepository logisticsRepository;
    @Autowired
    Mapper mapper;

    public void saveOders(List<OrderDTO> ordersDto) {
        List<Order> orders = mapper.orderDtosToEntityList(ordersDto);
        orderRepository.saveAll(orders);
    }

    public void saveLogistics(List<LogisticsDTO> logisticsDto) {
        List<Logistics> logisticsList = mapper.logisticsListToEntityList(logisticsDto);
        logisticsRepository.saveAll(logisticsList);
    }

    public void saveManufacture(List<ManufactureDTO> manufacturesDto) {
        List<Manufacture> manufactures = mapper.manufactureDtoToEntityList(manufacturesDto);
        manufactureRepository.saveAll(manufactures);
    }

    public void saveSales(List<SalesDTO> salesDto) {
        List<Sales> sales = mapper.salesDtoListToEntityList(salesDto);
        salesRepository.saveAll(sales);
    }
}
