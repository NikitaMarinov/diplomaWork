package com.diploma.mapper;


import com.diploma.avro.LogisticsDTO;
import com.diploma.avro.ManufactureDto;
import com.diploma.avro.OrderDTO;
import com.diploma.model.Location;
import com.diploma.model.Order;
import com.diploma.model.dto.LocationDto;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface Mapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "product.brand", target = "brand")
    @Mapping(source = "product.model", target = "model")
    @Mapping(source = "migrationId", target = "id")
    OrderDTO toDto(Order order);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "product.brand", target = "brand")
    @Mapping(source = "product.model", target = "model")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.city", target = "city")
    @Mapping(source = "location.country", target = "country")
    @Mapping(source = "location.location", target = "location")
    @Mapping(source = "location.distanceToWarehouse", target = "distanceToWarehouse")
    @Mapping(source = "transport.id", target = "transportId")
    @Mapping(source = "transport.carType", target = "carType")
    @Mapping(source = "transport.speed", target = "speed")
    @Mapping(source = "transport.loadVolume", target = "loadVolume")
    @Mapping(source = "migrationId", target = "id")
    @Mapping(source = "deliveryEndTime", target = "deliveryEndTime", qualifiedByName = "zonedDateTimeToInstant")
    LogisticsDTO toLogisticsDto(Order order);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "id", target = "migrationId")
    @Mapping(source = "locationId", target = "location.id")
    Order toEntity(OrderDTO orderDTO);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "id", target = "migrationId")
    @Mapping(source = "locationId", target = "location.id")
    Order toEntityFromManufacture(ManufactureDto manufactureDto);

    List<OrderDTO> toDtoList(List<Order> orders);
    List<LogisticsDTO> toLogisticsDtoList(List<Order> orders);
    List<Order> toEntityList(List<OrderDTO> orderDTOs);
    List<Order> toEntityListFromManufacture(List<ManufactureDto> manufactureDtos);

    LocationDto toDto(Location location);
    Location toEntity(LocationDto locationDto);

    default String map(CharSequence value) {
        return value == null ? null : value.toString();
    }

    @Named("zonedDateTimeToInstant")
    default Instant zonedDateTimeToInstant(ZonedDateTime zonedDateTime) {
        return zonedDateTime != null ? zonedDateTime.toInstant() : null;
    }

    @Named("instantToZonedDateTime")
    default ZonedDateTime instantToZonedDateTime(Instant instant) {
        return instant != null ? instant.atZone(ZoneId.of("UTC")) : null;
    }

}
