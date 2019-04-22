package com.tutorial.controller;

import com.tutorial.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Locale;

/**
 * @Author: jimmy
 * @Date: 2019/4/16
 */
@RestController
public class MockValidController {
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/mockSave")
    public String save(@Valid @RequestBody User user, BindingResult result){
        System.out.println(user);
        if(result.hasErrors()){
            StringBuilder str = new StringBuilder();
            //获取错误字段集合
            List<FieldError> fieldErrors = result.getFieldErrors();
            //获取本地Local,zh_CN
            Locale local = LocaleContextHolder.getLocale();
            //遍历错误字段获取错误信息
            for(FieldError error:fieldErrors){
                //获取错误信息
                String errorMsg = messageSource.getMessage(error,local);
                str.append(error.getField()+":"+errorMsg+",");
            }
            return str.toString();
        }
        return "success";
    }

    @RequestMapping("/delUser")
    public String delUser(@NotBlank Long id, @NotEmpty String name){
        Assert.isTrue(id == null, "id为空");
        Assert.isTrue(StringUtils.isNotEmpty(name), "name为空");
        return "success";
    }
}
