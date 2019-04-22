package com.tutorial.init;

import com.tutorial.abstr.Vehicle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * InitializingBean接口为bean提供了初始化方法的方式,它只有afterPropertiesSet方法,凡是继承该接口的类,在初始化bean的时候会执行该方法
 * @Author: jimmy
 * @Date: 2019/4/15
 */
@Component
public class VehicleInit implements ApplicationContextAware,InitializingBean {

    private Map<String, Vehicle> vehicleMap = null;

    @Override
    public void afterPropertiesSet() {
        System.out.println("init vehicle");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        vehicleMap = applicationContext.getBeansOfType(Vehicle.class);
        System.out.println(vehicleMap);
        //{aircraft=com.tutorial.abstr.impl.AircraftVehicle@3900169, car=com.tutorial.abstr.impl.CarVehicle@31c46455}
        Vehicle car = vehicleMap.get("car");
        System.out.println(car.travel());
    }


    public String getVehicle(){
        System.out.println("-===========");
        return "success";
    }
}
