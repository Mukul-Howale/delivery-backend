package com.delivery.Delivery_app.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class AutoGenerate {

    @Id
    private Long one;
    private Long val;
    
}
