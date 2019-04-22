package com.tutorial.mapmodel;

import com.tutorial.entity.People;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * componentModel = "spring" 固定写spring
 * @Author: jimmy
 * @Date: 2019/4/18
 */
@Mapper(componentModel = "spring",uses = CustomConvert.class)
public interface PeopleModelMapper2 {
    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "name",source = "userName"),
            @Mapping(target = "birthDay",source = "birth",dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "email",ignore = true)})
    PeopleModel convert(People people);

    List<PeopleModel> convertList(List<People> list);
}
