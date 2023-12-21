package com.delivery.Delivery_app.controller;

import com.delivery.Delivery_app.entity.Food;
import com.delivery.Delivery_app.entity.Restaurant;
import com.delivery.Delivery_app.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodService foodService;


//    @PreAuthorize("hasAnyRole('ADMIN')")
    //    get all food endpoint
    @GetMapping("")
    public ResponseEntity<List<Food>> getAllFoods(){
        List<Food> foodList = foodService.getAllFoods();
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    //    get food endpoint
    @GetMapping("/{id}")
    public ResponseEntity<Food> getRestaurant(@PathVariable("id") long food_id){
        Food food = foodService.getFood(food_id);
        return new ResponseEntity<>(food,HttpStatus.OK);
    }

    //    add food endpoint
    @PostMapping("/restaurant/{id}")
    public ResponseEntity<Food> addFood(@RequestBody Food food,@PathVariable("id") long restaurant_id){
        Food food1 = foodService.addFood(food,restaurant_id);
        return new ResponseEntity<>(food1,HttpStatus.OK);
    }

    //    update existing food
    @PutMapping("")
    public ResponseEntity<String> updateFood(@RequestBody Food food){
        boolean flag = foodService.updateFood(food);
        if(flag){
            String msg = "Updated Successfully";
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }else{
            String msg = "Updated Unsuccessful";
            return new ResponseEntity<>(msg,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //  Delete food from existing db
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("id") long food_id ){
        boolean flag = foodService.deleteFood(food_id);
        if(flag){
            String msg = "Deleted Successfully";
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }else{
            String msg = "Deleted Unsuccessful";
            return new ResponseEntity<>(msg,HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
