package com.tutorial.abstr.impl;

import com.tutorial.abstr.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: jimmy
 * @Date: 2019/4/15
 */
//@Service("aircraft")
@Service
@Slf4j
public class AircraftVehicle extends Vehicle {

    @Override
    public String travel(){
        return "air";
    }
}
