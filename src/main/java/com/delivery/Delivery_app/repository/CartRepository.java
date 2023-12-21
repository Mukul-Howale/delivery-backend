package com.delivery.Delivery_app.repository;

import com.delivery.Delivery_app.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

    // Inserting new food item to the cart
    @Modifying
    @Query(value = "insert into cart(CART_ID,FOOD_ID,QUANTITY) values (:cartId, :foodId, :quantity)",
    nativeQuery = true)
    void addFoodToCart(Long cartId, Long foodId, Long quantity);

    // Check if the food is already in the cart
    @Query(value = "select COUNT(*) from cart where CART_ID = :cartId and FOOD_ID = :foodId",
    nativeQuery = true)
    int checkIfFoodInCart(Long cartId, Long foodId);

    //Updating a quantity of the food in the cart
    @Modifying
    @Query(value = "update cart set QUANTITY = :quantity where CART_ID = :cartId and FOOD_ID = :foodId",
    nativeQuery = true)
    void updateQuantity(Long cartId,Long foodId,Long quantity);

    // Deleting food item from the cart
    @Modifying
    @Query(value = "delete from cart where CART_ID = :cartId and FOOD_ID = :foodId",
    nativeQuery = true)
    void deleteFoodFromCart(Long cartId, Long foodId);

    // Getting all the list of food in a cart
    @Query(value = "select * from cart where CART_ID = :cartId",nativeQuery = true)
    List<Cart> getListOfFood(Long cartId);

    // Getting any single food item to get the restaurant address
    @Query(value = "select * from cart where CART_ID = :cartId limit 1",nativeQuery = true)
    Cart getSingleFoodItem(Long cartId);

    // Deleting the cart from the cart table
    @Modifying
    @Query(value = "delete from cart where CART_ID = :cartId", nativeQuery = true)
    void deleteCartData(Long cartId);
}
