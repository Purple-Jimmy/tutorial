package com.java.annotation;

import lombok.Data;

/**
 * @author jimmy
 * @date 2019-05-07 22:29
 */
@Data
public class ColourEntity {

    private Long id;

    @ColourAnnotation()
    private String colourName;

    private String name;
}
