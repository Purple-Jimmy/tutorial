package com.tutorial.mapmodel;

import com.tutorial.entity.People;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2019/4/18
 */
@Mapper
public interface PeopleModelMapper {
    PeopleModelMapper INSTANCE = Mappers.getMapper(PeopleModelMapper.class);

    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "name",source = "userName"),
            @Mapping(target = "birthDay",source = "birth",dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "email",ignore = true)})
    PeopleModel convert(People people);

    List<PeopleModel> convertList(List<People> list);

    /**
     * 转换时如果属性中有类型是Boolean,将会默认调用此方法转换
     * @param value
     * @return
     */
    default String convert2Bool(Boolean value) {
       if(value){
           return "man";
       }else {
           return "woman";
       }
    }

}
