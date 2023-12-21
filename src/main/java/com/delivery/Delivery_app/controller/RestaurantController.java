package com.delivery.Delivery_app.controller;

import com.delivery.Delivery_app.entity.Restaurant;
import com.delivery.Delivery_app.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

//    get all restaurant endpoint
    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);

    }

//    get restaurant endpoint
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id") long restaurant_id){
        Restaurant restaurant = restaurantService.getRestaurant(restaurant_id);
        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }

//    add restaurant endpoint
    @PostMapping("")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant){
        Restaurant restaurant1 = restaurantService.addRestaurant(restaurant);
        return new ResponseEntity<>(restaurant1, HttpStatus.OK);
    }

//    update existing restaurant
    @PutMapping("")
    public ResponseEntity<String> updateRestaurant(@RequestBody Restaurant restaurant){
        boolean flag = restaurantService.updateRestaurant(restaurant);
        if(flag){
            String msg = "Updated Successfully";
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }else{
            String msg = "Updated Unsuccessful";
            return new ResponseEntity<>(msg,HttpStatus.NOT_ACCEPTABLE);
        }

    }

//  Delete restaurant from existing db
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("id") long restaurant_id ){
        boolean flag = restaurantService.deleteRestaurant(restaurant_id);
        if(flag){
            String msg = "Deleted Successfully";
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }else{
            String msg = "Deleted Unsuccessful";
            return new ResponseEntity<>(msg,HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
