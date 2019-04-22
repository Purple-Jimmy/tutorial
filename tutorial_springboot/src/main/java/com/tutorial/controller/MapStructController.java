package com.tutorial.controller;

import com.tutorial.entity.People;
import com.tutorial.mapmodel.PeopleModel;
import com.tutorial.mapmodel.PeopleModelMapper;
import com.tutorial.mapmodel.PeopleModelMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: jimmy
 * @Date: 2019/4/18
 */
@RestController
public class MapStructController {
    @Autowired
    PeopleModelMapper2 peopleModelMapper2;

    @RequestMapping("/convertDTO")
    public Object convertDTO(){
        People people = new People();
        people.setId(1L);
        people.setUserName("Jimmy");
        people.setBirth(new Date());
        people.setSex(true);
        PeopleModel peopleModel = PeopleModelMapper.INSTANCE.convert(people);
        return peopleModel;
    }

    /**
     * spring 注入方式
     * @return
     */
    @RequestMapping("/convertDTO2")
    public Object convertDTOWithSpring(){
        People people = new People();
        people.setId(2L);
        people.setUserName("Jimmy");
        people.setBirth(new Date());
        people.setSex(true);
        PeopleModel peopleModel = peopleModelMapper2.convert(people);
        return peopleModel;
    }

}
