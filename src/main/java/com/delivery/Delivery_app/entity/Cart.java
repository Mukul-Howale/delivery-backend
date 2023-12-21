package com.delivery.Delivery_app.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Cart_ID")
    private Long cartId;

    @Column(name = "Food_ID")
    private Long foodId;

    @Column(name = "Quantity")
    private Long quantity;

}
