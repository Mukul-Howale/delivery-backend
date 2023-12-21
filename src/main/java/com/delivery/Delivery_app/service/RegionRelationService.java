package com.delivery.Delivery_app.service;

import com.delivery.Delivery_app.entity.Region;
import com.delivery.Delivery_app.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionRelationService {

    @Autowired
    RegionRepository regionRepository;

    public void addRelation(List<Region> regionRelation) {

        for(Region rr : regionRelation){
            regionRepository.save(rr);
        }
    }
}
