package com.diploma.mapper;


import com.diploma.avro.LogisticsDTO;
import com.diploma.avro.ManufactureDTO;
import com.diploma.avro.OrderDTO;
import com.diploma.avro.SalesDTO;
import com.diploma.model.Logistics;
import com.diploma.model.Manufacture;
import com.diploma.model.Order;
import com.diploma.model.Sales;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface Mapper {

    @Mapping(source = "orderDate", target = "orderDate", qualifiedByName = "zonedDateTimeToInstant")
    OrderDTO orderToDto(Order order);

    @Mapping(source = "orderDate", target = "orderDate", qualifiedByName = "instantToZonedDateTime")
    Order orderDtoToEntity(OrderDTO orderDTO);

    List<OrderDTO> ordersToDtoList(List<Order> orders);

    List<Order> orderDtosToEntityList(List<OrderDTO> orderDTOs);

    @Mapping(source = "productionEndTime", target = "productionEndTime", qualifiedByName = "zonedDateTimeToInstant")
    ManufactureDTO manufactureToDto(Manufacture manufacture);

    @Mapping(source = "productionEndTime", target = "productionEndTime", qualifiedByName = "instantToZonedDateTime")
    Manufacture manufactureDtoToEntity(ManufactureDTO manufactureDTO);

    List<ManufactureDTO> manufacturesToDtoList(List<Manufacture> manufactures);

    List<Manufacture> manufactureDtoToEntityList(List<ManufactureDTO> manufactureDTOS);

    @Mapping(source = "orderDate", target = "orderDate", qualifiedByName = "zonedDateTimeToInstant")
    SalesDTO salesToDto(Sales sales);
    @Mapping(source = "orderDate", target = "orderDate", qualifiedByName = "instantToZonedDateTime")
    Sales salesDtoToEntity(SalesDTO salesDTO);

    List<SalesDTO> salesListToDtoList(List<Sales> sales);

    List<Sales> salesDtoListToEntityList(List<SalesDTO> salesDTOS);

    @Mapping(source = "deliveryEndTime", target = "deliveryEndTime", qualifiedByName = "zonedDateTimeToInstant")
    LogisticsDTO logisticsToDto(Logistics logistics);

    @Mapping(source = "deliveryEndTime", target = "deliveryEndTime", qualifiedByName = "instantToZonedDateTime")
    Logistics logisticsDtoToEntity(LogisticsDTO logisticsDTO);

    List<LogisticsDTO> logisticsListToDtoList(List<Logistics> logistics);

    List<Logistics> logisticsListToEntityList(List<LogisticsDTO> logisticsDTOS);

    @Named("zonedDateTimeToInstant")
    default Instant zonedDateTimeToInstant(ZonedDateTime zonedDateTime) {
        return zonedDateTime != null ? zonedDateTime.toInstant() : null;
    }

    @Named("instantToZonedDateTime")
    default ZonedDateTime instantToZonedDateTime(Instant instant) {
        return instant != null ? instant.atZone(ZoneId.of("UTC")) : null;
    }

    default String map(CharSequence value) {
        return value == null ? null : value.toString();
    }
}
