package com.person.springdemo.mapper;


import com.person.springdemo.pojo.Category1;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;


//mybatis 注解方式
@Mapper
@Component
public interface CategoryMapper {

    @Select("select * from category_")
    List<Category1> findAll();

    @Insert(" insert into category_ ( name ) values (#{name}) ")
    public int save(Category1 category);

    @Delete(" delete from category_ where id= #{id} ")
    public void delete(int id);

    @Select("select * from category_ where id= #{id} ")
    public Category1 get(int id);

    @Update("update category_ set name=#{name} where id=#{id} ")
    public int update(Category1 category);

}
