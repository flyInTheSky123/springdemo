package com.person.springdemo.controller;

import com.person.springdemo.dao.CategoryDAO;
import com.person.springdemo.mapper.CategoryMapper;
import com.person.springdemo.pojo.Category;
import com.person.springdemo.pojo.Category1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController {

    /**
     * //Jpa 注解方式
     *
     * @Autowired CategoryDAO categoryDAO;
     * @RequestMapping("/listCategory") public String listCategory(Model m) throws Exception{
     * List<Category> all = categoryDAO.findAll();
     * m.addAttribute("cs",all);
     * return "listCategory";
     * <p>
     * }
     **/


    //mybatis注解方式
    @Autowired
    CategoryMapper categoryMapper;
    @RequestMapping("/listCategory")
    public String listCategory(Model m) throws Exception {
        List<Category1> cs = categoryMapper.findAll();
        m.addAttribute("cs", cs);
        return "listCategory";
    }


}
