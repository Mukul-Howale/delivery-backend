package com.delivery.Delivery_app.repository;

import com.delivery.Delivery_app.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    // Getting list of to-regions that could be reached from from-region
    @Query(value = "select toRegion from Region where fromRegion = :userAddress")
    List<String> getRelation(String userAddress);

}
