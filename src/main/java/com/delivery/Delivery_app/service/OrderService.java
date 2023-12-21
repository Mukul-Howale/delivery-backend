package com.delivery.Delivery_app.service;

import com.delivery.Delivery_app.controller.AutoGenerateController;
import com.delivery.Delivery_app.dto.CartDTO;
import com.delivery.Delivery_app.dto.OrderDTO;
import com.delivery.Delivery_app.entity.*;
import com.delivery.Delivery_app.exception.WrongIDException;
import com.delivery.Delivery_app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    AutoGenerateController autoGenerateController;

    @Autowired
    CartService cartService;

    public Long makeOrder(Long userId, Long cartId) {

        if(userRepository.findById(userId).isEmpty()){
            throw new WrongIDException("No user present with such ID");
        }
        if(cartRepository.getSingleFoodItem(cartId) == null){
            throw new WrongIDException("No cart present with such ID");
        }

        Address address = userRepository.findById(userId).get().getAddress();

        String userAddress = address.toString();

        Cart foodItem =  cartRepository.getSingleFoodItem(cartId);

        String restaurantAddress = foodRepository.findById(foodItem.getFoodId()).get().getRestaurant().getRestaurant_address();

        String estimatedTime = estimatedTime(userAddress,restaurantAddress);

        Long totalAmount = totalAmountOfCart(cartRepository.getListOfFood(cartId));

        Long orderID = orderRepository.save(new Order(cartId,userId,totalAmount,getCurrentTime().getTime().toString(),estimatedTime,"Initialized")).getOrderId();

        User u = userRepository.findById(userId).get();

        u.setCartId(autoGenerateController.getValue());

        userRepository.save(u);

        return orderID;
    }

    public void cancelOrder(Long orderId) {

        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isEmpty()){
            throw new WrongIDException("No order present with such ID");
        }

        Order order1 = order.get();

        order1.setStatus("Canceled");

        orderRepository.save(order1);
    }

    public void successOrder(Long orderId) {

        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isEmpty()){
            throw new WrongIDException("No order present with such ID");
        }
        Order order1 = order.get();

        order1.setStatus("Successful");

        orderRepository.save(order1);
    }

    public List<Order> getAllCurrentOrders(Long userId) {

        if(userRepository.findById(userId).isEmpty()){
            throw new WrongIDException("No user present with such ID");
        }

        return orderRepository.getAllCurrentOrders(userId,"Initialized");
    }

    public List<Order> orderHistory(Long userId) {

        if(userRepository.findById(userId).isEmpty()){
            throw new WrongIDException("No user present with such ID");
        }

        return orderRepository.orderHistory(userId,"Canceled","Successful");
    }

    public OrderDTO getOrder(Long orderId) {

        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isEmpty()){
            throw new WrongIDException("No order present with such ID");
        }

        List<CartDTO> cartDTOList = cartService.getFoodListInCart(order.get().getCartId());

        return new OrderDTO(order.get().getOrderedTime(),order.get().getEstimatedTime(),order.get().getStatus(),order.get().getTotalAmount(),cartDTOList);
    }

    private String estimatedTime(String userAddress, String restaurantAddress){

        int jumps = findMinimumDistance(userAddress,restaurantAddress);

        Calendar tempTime = getCurrentTime();

        if(jumps == 0){
            tempTime.add(Calendar.MINUTE,randomNumberGenerator(15,25));
        }
        else if(jumps == 1){
            tempTime.add(Calendar.MINUTE,randomNumberGenerator(20,30));
        }
        else if(jumps == 2){
            tempTime.add(Calendar.MINUTE,randomNumberGenerator(30,40));
        }
        else if(jumps == 3){
            tempTime.add(Calendar.MINUTE,randomNumberGenerator(40,50));
        }
        else if(jumps == 4){
            tempTime.add(Calendar.MINUTE,randomNumberGenerator(50,60));
        }
        else if(jumps == 5){
            tempTime.add(Calendar.MINUTE,randomNumberGenerator(60,80));
        }
        return tempTime.getTime().toString();
    }

    private int findMinimumDistance(String userAddress, String restaurantAddress){

        Queue<String> queue = new LinkedList<>();

        HashMap<String,Integer> map = new HashMap<>();

        queue.add(userAddress);

        map.put(userAddress,0);

        while(!queue.isEmpty()) {

            String s = queue.remove();

            List<String> list = regionRepository.getRelation(s);

            for (String temp : list) {
                if (!map.containsKey(temp)) {
                    queue.add(temp);
                    map.put(temp, map.get(s) + 1);
                }
            }
        }
        return map.get(restaurantAddress);
    }

    private Long totalAmountOfCart(List<Cart> cartList){
        Long sum = 0l;
        for (Cart c : cartList){
            sum = sum + (foodRepository.findById(c.getFoodId()).get().getFood_price() * c.getQuantity());
        }
        return sum;
    }

    private Calendar getCurrentTime(){
        return Calendar.getInstance();
    }

    private int randomNumberGenerator(int a, int b){
        return (int)(Math.random() * (b - a)) + a;
    }

}
