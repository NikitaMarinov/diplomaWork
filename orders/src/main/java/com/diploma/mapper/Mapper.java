package com.diploma.mapper;

import com.diploma.model.Order;
import com.diploma.avro.OrderDTO;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface Mapper {

    @Mapping(source = "product.id", target = "productId")
    OrderDTO toDto(Order order);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "customerName", source = "customerName", qualifiedByName = "charSequenceToString")
    Order toEntity(OrderDTO orderDTO);

    @Named("charSequenceToString")
    default String charSequenceToString(CharSequence value) {
        return value != null ? value.toString() : null;
    }
    List<OrderDTO> toDtoList(List<Order> orders);

    List<Order> toEntityList(List<OrderDTO> orderDTOs);
}
