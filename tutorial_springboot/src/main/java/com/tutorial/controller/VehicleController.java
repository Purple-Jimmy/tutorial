package com.tutorial.controller;

import com.tutorial.init.VehicleInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jimmy
 * @Date: 2019/4/15
 */
@RestController
public class VehicleController {
    @Autowired(required = true)
    VehicleInit vehicleInit;

    @RequestMapping("/vehicleTest")
    public String vehicleTest(){
        return vehicleInit.getVehicle();
    }
}
