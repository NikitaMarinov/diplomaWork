package com.diploma.mapper;


import com.diploma.avro.LogisticsDTO;
import com.diploma.avro.ManufactureDTO;
import com.diploma.avro.OrderDTO;
import com.diploma.avro.SalesDTO;
import com.diploma.model.Logistics;
import com.diploma.model.Manufacture;
import com.diploma.model.Order;
import com.diploma.model.Sales;
import org.mapstruct.MappingConstants;

import java.util.List;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface Mapper {


    OrderDTO orderToDto(Order order);

    Order orderDtoToEntity(OrderDTO orderDTO);

    List<OrderDTO> ordersToDtoList(List<Order> orders);

    List<Order> orderDtosToEntityList(List<OrderDTO> orderDTOs);

    ManufactureDTO manufactureToDto(Manufacture manufacture);

    Manufacture manufactureDtoToEntity(ManufactureDTO manufactureDTO);

    List<ManufactureDTO> manufacturesToDtoList(List<Manufacture> manufactures);

    List<Manufacture> manufactureDtoToEntityList(List<ManufactureDTO> manufactureDTOS);

    SalesDTO salesToDto(Sales sales);

    Sales salesDtoToEntity(SalesDTO salesDTO);

    List<SalesDTO> salesListToDtoList(List<Sales> sales);

    List<Sales> salesDtoListToEntityList(List<SalesDTO> salesDTOS);

    LogisticsDTO logisticsToDto(Logistics logistics);

    Logistics logisticsDtoToEntity(LogisticsDTO logisticsDTO);

    List<LogisticsDTO> logisticsListToDtoList(List<Logistics> logistics);

    List<Logistics> logisticsListToEntityList(List<LogisticsDTO> logisticsDTOS);




    default String map(CharSequence value) {
        return value == null ? null : value.toString();
    }
}
