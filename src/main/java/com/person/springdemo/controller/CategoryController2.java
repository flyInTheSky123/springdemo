package com.person.springdemo.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.person.springdemo.config.PageHelperConfig;
import com.person.springdemo.dao.CategoryDAO;
import com.person.springdemo.mapper.CategoryMapper;
import com.person.springdemo.pojo.Category;
import com.person.springdemo.pojo.Category1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


//Restful 风格的JPA
@Controller
public class CategoryController2 {
    @Autowired
    CategoryDAO categoryDAO;

    @GetMapping("/categories")
    public String listCategory(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
//        start = start < 0 ? 0 : start;
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(start, size, sort);

        start = start < 0 ? 0 : start;
        //以id 逆序排序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        //页面数据条数 。start=0,size=5 ，sort顺序。
        PageRequest pageRequest = PageRequest.of(start, size, sort);
        Page<Category> page = categoryDAO.findAll(pageRequest);
        m.addAttribute("page", page);
        return "listCategory2";
    }

    @PostMapping("/categories")
    public String addCategory(Category c) throws Exception {
        categoryDAO.save(c);
        return "redirect:/categories";
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(Category c) throws Exception {
        categoryDAO.delete(c);
        return "redirect:/categories";
    }

    @PutMapping("/categories/{id}")
    public String updateCategory(Category c) throws Exception {
        categoryDAO.save(c);
        return "redirect:/categories";
    }

    @GetMapping("/categories/{id}")
    public String getCategory(@PathVariable("id") int id, Model m) throws Exception {
        Category c = categoryDAO.getOne(id);
        m.addAttribute("c", c);
        return "editCategory2";
    }
}