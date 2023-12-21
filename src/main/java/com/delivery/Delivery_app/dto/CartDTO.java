package com.delivery.Delivery_app.dto;

import com.delivery.Delivery_app.entity.Food;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartDTO {

    private Food food;
    private Long quantity;

}
