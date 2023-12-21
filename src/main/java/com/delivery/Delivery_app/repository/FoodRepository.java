package com.delivery.Delivery_app.repository;

import com.delivery.Delivery_app.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
}
