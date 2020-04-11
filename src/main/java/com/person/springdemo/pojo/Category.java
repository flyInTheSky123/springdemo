package com.person.springdemo.pojo;

import javax.persistence.*;
//以下是 springboot jap 的注解方式。没有mapper。


@Entity   //表明这是一个实体类
@Table(name = "category_")  //对应的数据库表名
public class Category {

    @Id     //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自动增加
    @Column(name = "id")   //对应的数据库字段
    private int id ;

    @Column(name = "name")
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
