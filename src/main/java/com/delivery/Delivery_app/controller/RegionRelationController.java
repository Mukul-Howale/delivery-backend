package com.delivery.Delivery_app.controller;

import com.delivery.Delivery_app.entity.Region;
import com.delivery.Delivery_app.service.RegionRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionRelationController {

    @Autowired
    RegionRelationService regionRelationService;

    @PostMapping("/relation")
    public ResponseEntity addRelation(@RequestBody List<Region> regionRelation){

        regionRelationService.addRelation(regionRelation);

        return new ResponseEntity("Relations added",HttpStatus.OK);
    }
}
