package com.person.springdemo.mapper;


import com.person.springdemo.pojo.Category1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;


//mybatis 注解方式
@Mapper
@Component
public interface CategoryMapper {

    @Select("select * from category_")
    List<Category1> findAll();

}
