package com.tutorial.entity;

import com.tutorial.anno.valid.CustomValidator;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: jimmy
 * @Date: 2019/4/15
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 4681223794728133986L;

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;

    @CustomValidator(values = "w,m")
    private String sex;
}
