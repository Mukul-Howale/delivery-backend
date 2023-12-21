package com.delivery.Delivery_app.repository;

import com.delivery.Delivery_app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    // Getting list of current orders
    @Query(value = "select o from Order o where o.userId = :userId and o.status = :stat")
    List<Order> getAllCurrentOrders(Long userId,String stat);

    // Getting list of successful and canceled orders
    @Query(value = "select o from Order o where o.userId = :userId and o.status = :canceled or o.status = :successful")
    List<Order> orderHistory(Long userId, String canceled, String successful);
}