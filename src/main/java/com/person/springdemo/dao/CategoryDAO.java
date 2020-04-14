package com.person.springdemo.dao;

import com.person.springdemo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

//category 是类型，Integer 是主键类型
public interface CategoryDAO extends JpaRepository<Category,Integer> {
//JPA 条件查询方式很有意思，是不需要写 SQL 语句的，只需要在 dao 接口里按照规范的命名定义对应的方法名，及可达到查询相应字段的效果了。

    //通过name 查询
    public List<Category> findByName(String name);
    //模糊查询和id要大于指定数量
    public List<Category> findByNameLikeAndIdGreaterThanOrderByNameAsc(String name, int id);

}
