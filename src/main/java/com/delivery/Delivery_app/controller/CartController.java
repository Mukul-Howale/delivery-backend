package com.delivery.Delivery_app.controller;

import com.delivery.Delivery_app.dto.CartDTO;
import com.delivery.Delivery_app.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/addFood/cartId/{cartId}/foodId/{foodId}/quantity/{quantity}")
    public ResponseEntity<HttpStatus> addFoodToCart(@PathVariable("cartId") Long cartId,
                                        @PathVariable("foodId") Long foodId,
                                        @PathVariable("quantity") Long quantity){

        cartService.addFoodToCart(cartId,foodId,quantity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteFood/cartId/{cartId}/foodId/{foodId}")
    public ResponseEntity<HttpStatus> deleteFoodFromCart(@PathVariable("cartId") Long cartId,
                                             @PathVariable("foodId") Long foodId){

        cartService.deleteFoodFromCart(cartId,foodId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteCart/{cartId}")
    public ResponseEntity<HttpStatus> deleteCartData(@PathVariable("cartId") Long cartId){

        cartService.deleteCartData(cartId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/foodList/{cartId}")
    public ResponseEntity<CartDTO> getFoodListInCart(@PathVariable("cartId") Long cartId){

        List<CartDTO> foodList = cartService.getFoodListInCart(cartId);

        return new ResponseEntity(foodList,HttpStatus.OK);
    }
}
