package com.diploma.mapper;


import com.diploma.avro.OrderDTO;
import com.diploma.model.Location;
import com.diploma.model.Order;
import com.diploma.model.dto.LocationDto;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface Mapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "migrationId", target = "id")
    OrderDTO toDto(Order order);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "id", target = "migrationId")
    @Mapping(source = "locationId", target = "location.id")
    Order toEntity(OrderDTO orderDTO);

    List<OrderDTO> toDtoList(List<Order> orders);

    List<Order> toEntityList(List<OrderDTO> orderDTOs);

    LocationDto toDto(Location location);
    Location toEntity(LocationDto locationDto);

    default String map(CharSequence value) {
        return value == null ? null : value.toString();
    }
}
