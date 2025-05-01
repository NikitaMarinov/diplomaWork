package com.diploma.repository;

import com.diploma.constants.OrderStatus;
import com.diploma.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findOrdersByStatus(OrderStatus status);

    @Modifying
    @Query("UPDATE Order o SET o.status = :status WHERE o.id IN :ids")
    int updateStatusByIds(@Param("status") OrderStatus status, @Param("ids") List<Long> ids);
}