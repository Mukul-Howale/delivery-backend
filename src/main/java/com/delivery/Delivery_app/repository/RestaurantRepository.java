package com.delivery.Delivery_app.repository;

import com.delivery.Delivery_app.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
