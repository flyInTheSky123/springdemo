package com.person.springdemo.dao;

import com.person.springdemo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
//category 是类型，Integer 是主键类型
public interface CategoryDAO extends JpaRepository<Category,Integer> {

}
