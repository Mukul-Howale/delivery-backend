package com.delivery.Delivery_app.controller;

import com.delivery.Delivery_app.dto.OrderDTO;
import com.delivery.Delivery_app.entity.Order;
import com.delivery.Delivery_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/initialized/userId/{userId}/cartId/{cartId}")
    public ResponseEntity<URI> makeOrder(@PathVariable("userId") Long userId,
                                    @PathVariable("cartId") Long cartId){

        Long orderId = orderService.makeOrder(userId,cartId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/../../../../../{id}")
                .buildAndExpand(orderId)
                .normalize()
                .toUri();

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderId") Long orderId){

        orderService.cancelOrder(orderId);

        return new ResponseEntity<>("Order is canceled",HttpStatus.OK);
    }

    @PostMapping("/success/{orderId}")
    public ResponseEntity<String> successOrder(@PathVariable("orderId") Long orderId){

        orderService.successOrder(orderId);

        return new ResponseEntity<>("Order is successful",HttpStatus.OK);
    }

    @GetMapping ("/current/{userId}")
    public ResponseEntity<Order> getAllCurrentOrders(@PathVariable("userId") Long userId){

        List<Order> orderList = orderService.getAllCurrentOrders(userId);

        return new ResponseEntity(orderList,HttpStatus.OK);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<Order> orderHistory(@PathVariable("userId") Long userId){

        List<Order> orderList = orderService.orderHistory(userId);

        return new ResponseEntity(orderList,HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable("orderId") Long orderId){

        OrderDTO order = orderService.getOrder(orderId);

        return new ResponseEntity(order,HttpStatus.OK);
    }
}
