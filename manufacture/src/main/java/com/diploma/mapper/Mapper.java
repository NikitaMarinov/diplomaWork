package com.diploma.mapper;


import com.diploma.model.Order;
import com.diploma.avro.OrderDTO;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface Mapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "product.brand", target = "brand")
    @Mapping(source = "product.model", target = "model")
    @Mapping(source = "migrationId", target = "id")
    OrderDTO toDto(Order order);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "id", target = "migrationId")
    Order toEntity(OrderDTO orderDTO);

    List<OrderDTO> toDtoList(List<Order> orders);

    List<Order> toEntityList(List<OrderDTO> orderDTOs);

    default String map(CharSequence value) {
        return value == null ? null : value.toString();
    }
}
