package com.java.annotation;

import java.lang.annotation.*;

/**
 * @author jimmy
 * @date 2019-05-07 22:24
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColourAnnotation {

    public enum Colour {RED,GRREN};

    Colour colour() default Colour.GRREN;
    String lovePurple() default "love";
}
