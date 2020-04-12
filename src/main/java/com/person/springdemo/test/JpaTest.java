package com.person.springdemo.test;

import com.person.springdemo.SpringdemoApplication;
import com.person.springdemo.dao.CategoryDAO;
import com.person.springdemo.pojo.Category;
import com.person.springdemo.pojo.Category1;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringdemoApplication.class)
public class JpaTest {
    @Autowired
    CategoryDAO dao;


    @Before
    public void before() {
        List<Category> all = dao.findAll();
        for (Category c : all) {
            dao.delete(c);
        }

        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.setName(i + 1 + "号");
            dao.save(category);
        }

    }

    @Test
    public void test() {
        List<Category> cs = dao.findAll();
        for (Category c : cs) {
            System.out.println("c.getName():" + c.getName());
        }

    }


    //指定查询
    @Test
    public void test2() {
        System.out.println("查询名称是 \"category 1 \"的分类:");
        List<Category> cs=  dao.findByName("1号");
        for (Category c : cs) {
            System.out.println("c.getName():"+ c.getName());
        }
        System.out.println();
    }

    //模糊查询
    @Test
    public void test3(){
        System.out.println("模糊查询，id 要大于 x");
        List<Category> byNameAndIdGreaterThanOrderByIdIdAsc = dao.findByNameLikeAndIdGreaterThanOrderByNameAsc("%4%", 5);
        for (Category c:byNameAndIdGreaterThanOrderByIdIdAsc){
            System.out.println("name :"+c.getName()+" "+c.getId() );
        }


    }



}
