package com.delivery.Delivery_app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "Cart_ID")
    private Long cartId;

    @Column(name = "User_ID")
    private Long userId;

    @Column(name = "Total_Amount")
    private Long totalAmount;

    @Column(name = "Ordered_Time")
    private String orderedTime;

    @Column(name = "Estimated_Time")
    private String estimatedTime;

    @Column(name = "Status")
    private String status;

    public Order(Long cartId, Long userId, Long totalAmount,
                           String orderedTime, String estimatedTime, String status){
        this.cartId = cartId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderedTime = orderedTime;
        this.estimatedTime = estimatedTime;
        this.status = status;
    }
}
