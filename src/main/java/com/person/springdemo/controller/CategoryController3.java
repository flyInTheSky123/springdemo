package com.person.springdemo.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.PageHelper;
import com.person.springdemo.dao.CategoryDAO;
import com.person.springdemo.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//ajax 使用json 的方式提交数据。
/** @RequestParam 与@PathVariable 区别：
 *  1，category?id=1 使用 @RequestParam
 *  2, category/ 1 使用@PathVariable；
**/
@RestController
public class CategoryController3 {
    @Autowired
    CategoryDAO categoryDAO;


    @GetMapping("/category")
    public List<Category> listCategory(@RequestParam(value = "start", defaultValue = "0") int start,
                                       @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        //jpa版的pagehelper 顺序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        //设置jpa版的pagehelper 的start ,size ,sort
        PageRequest page = PageRequest.of(start, size, sort);
        Page<Category> all = categoryDAO.findAll(page);
        return all.getContent();
    }

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable("id") int id) {
        Category byId = categoryDAO.getOne(id);
        System.out.println(byId);

        return byId;
    }

    @PutMapping("/category")
    public void putCategory(@RequestBody Category category){
        System.out.println("springboot接受到浏览器以JSON格式提交的数据："+category);

    }
}
