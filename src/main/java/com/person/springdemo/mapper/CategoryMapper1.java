package com.person.springdemo.mapper;


import com.person.springdemo.pojo.Category1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


//mybatis xml方式
@Mapper
public interface CategoryMapper1 {
    List<Category1> findAll();
}
