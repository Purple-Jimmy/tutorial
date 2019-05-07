package com.annotation.demo;

import com.java.annotation.ColourEntity;
import org.junit.Test;

/**
 * @author jimmy
 * @date 2019-05-07 22:31
 */
public class AnnotationDemo {

    @Test
    public void test(){
        ColourEntity colourEntity1 = new ColourEntity();
        System.out.println(colourEntity1);

        ColourEntity colourEntity2 = new ColourEntity();
        colourEntity2.setColourName("yellow");
        System.out.println(colourEntity2);

    }
}
