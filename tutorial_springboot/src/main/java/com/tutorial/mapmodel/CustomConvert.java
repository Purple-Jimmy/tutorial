package com.tutorial.mapmodel;

import org.springframework.stereotype.Component;

/**
 * @author jimmy
 * @date 2019-04-18 20:50
 */
@Component
public class CustomConvert {

    public String asString(Boolean value){
        if(value){
            return "trueStr";
        }
        return "falseStr";
    }
}
