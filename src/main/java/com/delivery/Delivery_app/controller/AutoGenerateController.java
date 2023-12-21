package com.delivery.Delivery_app.controller;

import com.delivery.Delivery_app.entity.AutoGenerate;
import com.delivery.Delivery_app.repository.AutoGenerateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoGenerateController {

    @Autowired
    AutoGenerateRepository autoGenerateRepository;

    @GetMapping("/getAutoVal")
    public Long getValue(){
        long l = 1;
        if(autoGenerateRepository.existsById(l)) {
            AutoGenerate autoGenerate = autoGenerateRepository.findById(l).get();
            autoGenerate.setVal(autoGenerate.getVal()+1);
            autoGenerateRepository.save(autoGenerate);
            return autoGenerate.getVal();
        }
        else{
            AutoGenerate autoGenerate = new AutoGenerate();
            autoGenerate.setOne(l);
            autoGenerate.setVal(l);
            autoGenerateRepository.save(autoGenerate);
            return autoGenerate.getVal();
        }
    }

}
