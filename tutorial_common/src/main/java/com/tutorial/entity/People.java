package com.tutorial.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: jimmy
 * @Date: 2019/4/18
 */
@Data
public class People {
    private Long id;
    private String userName;
    private Date birth;
    private Boolean sex;
}
