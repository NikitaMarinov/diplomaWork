package com.diploma.mapper;

import com.diploma.avro.OrderDTO;
import com.diploma.avro.SalesDTO;
import com.diploma.model.Order;
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
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.city", target = "city")
    @Mapping(source = "location.country", target = "country")
    @Mapping(source = "migrationId", target = "id")
    @Mapping(source = "orderDate", target = "orderDate", qualifiedByName = "zonedDateTimeToInstant")
    OrderDTO toDto(Order order);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "product.brand", target = "brand")
    @Mapping(source = "product.model", target = "model")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.city", target = "city")
    @Mapping(source = "location.country", target = "country")
    @Mapping(source = "migrationId", target = "id")
    @Mapping(source = "orderDate", target = "orderDate", qualifiedByName = "zonedDateTimeToInstant")
    SalesDTO toSalesDto(Order order);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "locationId", target = "location.id")
    @Mapping(source = "id", target = "migrationId")
    @Mapping(source = "orderDate", target = "orderDate", qualifiedByName = "instantToZonedDateTime")
    Order toEntity(OrderDTO orderDTO);

    default String map(CharSequence value) {
        return value == null ? null : value.toString();
    }

    List<OrderDTO> toDtoList(List<Order> orders);
    List<SalesDTO> toDtoSalesList(List<Order> orders);

    List<Order> toEntityList(List<OrderDTO> orderDTOs);

    @Named("zonedDateTimeToInstant")
    default Instant zonedDateTimeToInstant(ZonedDateTime zonedDateTime) {
        return zonedDateTime != null ? zonedDateTime.toInstant() : null;
    }

    @Named("instantToZonedDateTime")
    default ZonedDateTime instantToZonedDateTime(Instant instant) {
        return instant != null ? instant.atZone(ZoneId.of("Europe/Moscow")) : null;
    }


}
