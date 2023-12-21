package com.delivery.Delivery_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long food_id;
    private String food_name;
    private String food_description;
    private String food_category;
    private Long food_price;

    @ManyToOne
    @JsonIgnore
    Restaurant restaurant;

}
