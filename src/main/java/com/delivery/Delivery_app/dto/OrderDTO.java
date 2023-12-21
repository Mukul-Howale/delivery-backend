package com.delivery.Delivery_app.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {

    String orderedTime;
    String estimatedTime;
    String status;
    Long amount;
    List<CartDTO> cart;
}
