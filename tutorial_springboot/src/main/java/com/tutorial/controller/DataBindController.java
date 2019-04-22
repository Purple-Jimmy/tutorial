package com.tutorial.controller;

import com.tutorial.databind.CityDataBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2019/4/15
 */
@RestController
public class DataBindController {

    @Autowired
    Environment environment;

    @RequestMapping("/cityBind")
    public Object cityBind(){
        Binder binder = Binder.get(environment);
        List<CityDataBind> cityDataBinds = binder.bind("spring.city", Bindable.listOf(CityDataBind.class)).get();
        return cityDataBinds;
    }
}
