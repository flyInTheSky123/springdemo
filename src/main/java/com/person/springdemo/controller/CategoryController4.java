package com.person.springdemo.controller;

import com.person.springdemo.pojo.Category;
import com.person.springdemo.service.CategoryService;
import com.person.springdemo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


//以Jpa作为持久层，redis作为非关系型数据库。
@Controller
public class CategoryController4 {

        @Autowired
        CategoryService categoryService;

        @RequestMapping("/listCategories")
        public String listCategory(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
            start = start < 0 ? 0 : start;
            //jpa版的pagehelper 顺序
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            //设置jpa版的pagehelper 的start ,size ,sort
            PageRequest pageRequest = PageRequest.of(start, size, sort);
            Page4Navigator<Category> page =categoryService.list(pageRequest);
            m.addAttribute("page", page);
            return "listCategory";
        }

        @RequestMapping("/addCategories")
        public String addCategory(Category c) throws Exception {
            categoryService.save(c);
            return "redirect:listCategories";
        }
        @RequestMapping("/deleteCategories")
        public String deleteCategory(Category c) throws Exception {
            categoryService.delete(c.getId());
            return "redirect:listCategories";
        }
        @RequestMapping("/updateCategories")
        public String updateCategory(Category c) throws Exception {
            categoryService.save(c);
            return "redirect:listCategories";
        }
        @RequestMapping("/editCategories")
        public String ediitCategory(int id,Model m) throws Exception {
            Category c= categoryService.get(id);
            m.addAttribute("c", c);
            return "editCategory";
        }

}
