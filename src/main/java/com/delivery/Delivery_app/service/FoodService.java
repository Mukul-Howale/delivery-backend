package com.delivery.Delivery_app.service;

import com.delivery.Delivery_app.entity.Food;
import com.delivery.Delivery_app.entity.Restaurant;
import com.delivery.Delivery_app.repository.FoodRepository;
import com.delivery.Delivery_app.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food getFood(long food_id) {
        Optional<Food> food = foodRepository.findById(food_id);
        if (food.isEmpty()) {
            return null;
        }
        return food.get();
    }

    public Food addFood(Food food,long restaurant_id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurant_id);
        if(restaurant.isEmpty()) return null;
    try{
        restaurant.get().addFood(food);
        food.setRestaurant(restaurant.get());
        return foodRepository.save(food);
    }catch (Exception ex){
        return null;
    }

    }

    public boolean updateFood(Food food) {
        Optional<Food> food1 = foodRepository.findById(food.getFood_id());
        if(food1.isEmpty()){
            return false;
        }

        try{
            food1.get().setFood_name(food.getFood_name());
            food1.get().setFood_category(food.getFood_category());
            food1.get().setFood_description(food.getFood_description());
            food1.get().setFood_price(food.getFood_price());
            foodRepository.save(food1.get());
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean deleteFood(long food_id) {
        Food food = foodRepository.findById(food_id).get();
        try{
            foodRepository.delete(food);
            return  true;
        }catch (Exception ex){
            return false;
        }
    }
}
