package com.delivery.Delivery_app.service;

import com.delivery.Delivery_app.dto.CartDTO;
import com.delivery.Delivery_app.entity.Cart;
import com.delivery.Delivery_app.entity.Food;
import com.delivery.Delivery_app.exception.WrongIDException;
import com.delivery.Delivery_app.repository.CartRepository;
import com.delivery.Delivery_app.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    FoodRepository foodRepository;

    //
    public void addFoodToCart(Long cartId, Long foodId, Long quantity) {

//        if(cartRepository.getSingleFoodItem(cartId) == null){
//            throw new WrongIDException("No cart present with such ID");
//        }
        if(foodRepository.findById(foodId).isEmpty()){
            throw new WrongIDException("No food item present with such ID");
        }

        int count = cartRepository.checkIfFoodInCart(cartId,foodId);

        if(count == 0) {
            cartRepository.addFoodToCart(cartId,foodId,quantity);
        }
        else{
            cartRepository.updateQuantity(cartId,foodId,quantity);
        }
    }

    public void deleteFoodFromCart(Long cartId, Long foodId) {
        if(cartRepository.getSingleFoodItem(cartId) == null){
            throw new WrongIDException("No cart present with such ID");
        }
        if(foodRepository.findById(foodId).isEmpty()){
            throw new WrongIDException("No food item present with such ID");
        }
        if(foodRepository.findById(cartRepository.getSingleFoodItem(cartId).getFoodId()).isEmpty()){
            throw new WrongIDException("No food item with such ID is present in the cart");
        }
        cartRepository.deleteFoodFromCart(cartId,foodId);
    }

    public List<CartDTO> getFoodListInCart(Long cartId) {
        if(cartRepository.getSingleFoodItem(cartId) == null){
            throw new WrongIDException("No cart present with such ID");
        }
        List<Cart> foodList = cartRepository.getListOfFood(cartId);

        List<CartDTO> cartDTOList = new ArrayList<>();

        for (Cart cart : foodList) {
            Food food = foodRepository.findById(cart.getFoodId()).get();

            cartDTOList.add(new CartDTO(food, cart.getQuantity()));
        }

        return cartDTOList;
    }

    public void deleteCartData(Long cartId) {

        if(cartRepository.getSingleFoodItem(cartId) == null){
            throw new WrongIDException("No cart present with such ID");
        }

        cartRepository.deleteCartData(cartId);
    }
}
