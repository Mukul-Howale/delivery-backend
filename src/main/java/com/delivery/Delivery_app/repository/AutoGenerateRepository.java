package com.delivery.Delivery_app.repository;

import com.delivery.Delivery_app.entity.AutoGenerate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoGenerateRepository extends JpaRepository<AutoGenerate,Long> {

}
