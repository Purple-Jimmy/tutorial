# MapStruct使用

## 引入pom
```
<dependency>
  <groupId>org.mapstruct</groupId>
  <artifactId>mapstruct-jdk8</artifactId>
  <version>1.3.0.Final</version>
</dependency>
<dependency>
  <groupId>org.mapstruct</groupId>
  <artifactId>mapstruct-processor</artifactId>
  <version>1.3.0.Final</version>
  <scope>provided</scope>
</dependency>
```

## 定义实体对象A和转换对象ADTO
## 定义mapper
````
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
}
````

## 使用
```
@RequestMapping("/convertDTO")
public Object convertDTO(){
    People people = new People();
    people.setId(1L);
    people.setUserName("Jimmy");
    people.setBirth(new Date());

    PeopleModel peopleModel = PeopleModelMapper.INSTANCE.convert(people);
    return peopleModel;
}
```

## 自定义转换
1. default()方式
```
default String convert2Bool(Boolean value) {
   if(value){
       return "man";
   }else {
       return "woman";
   }
}
```
2. uses注解方式
首先定义一个转换类
```
@Component
public class CustomConvert {

    public String asString(Boolean value){
        if(value){
            return "trueStr";
        }
        return "falseStr";
    }
}
```
在使用的时候加上注解uses
```
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
```


