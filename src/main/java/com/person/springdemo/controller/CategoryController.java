package com.person.springdemo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {


    //Jpa 注解方式
    // <jpa>
    @Autowired
    CategoryDAO categoryDAO;

    /**
     * @RequestMapping("/listCategory") //参数page 的satrt ，size长度
     * public String listCategory(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
     * <p>
     * <p>
     * //判断
     * start = start < 0 ? 0 : start;
     * //以id 逆序排序
     * Sort sort = Sort.by(Sort.Direction.DESC, "id");
     * <p>
     * //页面数据条数 。start=0,size=5 ，sort顺序。
     * PageRequest pageRequest = PageRequest.of(start, size, sort);
     * Page<Category> page = categoryDAO.findAll(pageRequest);
     * <p>
     * m.addAttribute("page", page);
     * <p>
     * return "listCategory";
     * }
     * @RequestMapping("/addCategory") public String addCategory(Category c) throws Exception {
     * categoryDAO.save(c);
     * return "redirect:listCategory";
     * }
     * @RequestMapping("/deleteCategory") public String deleteCategory(Category c) throws Exception {
     * categoryDAO.delete(c);
     * return "redirect:listCategory";
     * }
     * @RequestMapping("/updateCategory") public String updateCategory(Category c) throws Exception {
     * categoryDAO.save(c);
     * return "redirect:listCategory";
     * }
     * @RequestMapping("/editCategory") public String editCategory(int id, Model m) throws Exception {
     * Category c = categoryDAO.getOne(id);
     * m.addAttribute("c", c);
     * return "editCategory";
     * }
     * //</jpa>
     * <p>
     * }
     **/
    //mybatis注解方式.
    // start 是分页插件的起始位置 ,size 每页有size跳数据。
    @Autowired
    CategoryMapper categoryMapper;

    @RequestMapping("/listCategory")
    public String listCategory(Model m, @RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {

        //为分页 注入参数      start ,size，倒序排序
        PageHelper.startPage(start, size, "id desc");

        List<Category1> cs = categoryMapper.findAll();

        //创建pageinfo 对象。
        PageInfo<Category1> page = new PageInfo<>(cs);
        m.addAttribute("page", page);
        return "listCategory1";
    }


    @RequestMapping("/addCategory")
    public String listCategory(Category1 c) throws Exception {
        categoryMapper.save(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/deleteCategory")
    public String deleteCategory(Category1 c) throws Exception {
        categoryMapper.delete(c.getId());
        return "redirect:listCategory";
    }

    @RequestMapping("/updateCategory")
    public String updateCategory(Category1 c) throws Exception {
        categoryMapper.update(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/editCategory")
    public String listCategory(int id, Model m) throws Exception {
        Category1 c = categoryMapper.get(id);
        m.addAttribute("c", c);
        return "editCategory";
    }

}
