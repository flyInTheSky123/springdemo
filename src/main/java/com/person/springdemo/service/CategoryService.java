package com.person.springdemo.service;
import com.person.springdemo.pojo.Category;
import com.person.springdemo.util.Page4Navigator;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    public Page4Navigator<Category> list(Pageable pageable);

    public void save(Category category);

    public void delete(int id);

    public Category get(int id);
}