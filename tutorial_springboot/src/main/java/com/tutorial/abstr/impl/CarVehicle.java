package com.tutorial.abstr.impl;

import com.tutorial.abstr.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: jimmy
 * @Date: 2019/4/15
 */
@Service("car")
@Slf4j
public class CarVehicle extends Vehicle {

    @Override
    public String travel(){
        return "road";
    }
}
