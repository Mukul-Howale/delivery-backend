package com.delivery.Delivery_app.service;

import com.delivery.Delivery_app.entity.Restaurant;
import com.delivery.Delivery_app.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public boolean updateRestaurant(Restaurant restaurant) {
        Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurant.getRestaurant_id());
//        if (restaurant1.isEmpty()){
//            return new NullPointerException();
//        }
        try{
            restaurant1.get().setRestaurant_name(restaurant.getRestaurant_name());
            restaurant1.get().setRestaurant_address(restaurant.getRestaurant_address());
            restaurantRepository.save(restaurant1.get());
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean deleteRestaurant(long restaurant_id) {
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).get();
        try{
            restaurantRepository.delete(restaurant);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public Restaurant getRestaurant(long restaurant_id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurant_id);
        if(restaurant.isEmpty()){
            return null;
        }
        return restaurant.get();
    }
}
