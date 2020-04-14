package com.person.springdemo.service.impl;

import com.person.springdemo.dao.CategoryDAO;
import com.person.springdemo.pojo.Category;
import com.person.springdemo.service.CategoryService;
import com.person.springdemo.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//service 的继承
@Service
//CacheConfig，表示 分类数据在 redis 中都放在 category 这个分组里。
@CacheConfig(cacheNames = "category")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    /**
     * 假如是第一页，即offset=0，pageSize=5，那么会创建一个 key: "category 0-5";
     *     首先根据这个key 到 redis中查询数据。 第一次是不会有数据的，那么就会从数据库中取到这5条数据，然后以这个 key: "category 0-5" 保存到 redis 数据库中。
     *     下一次再次访问的时候，根据这个key，就可以从 redis 里取到数据了。
     * @param pageable
     * @return
     */
    @Override
    @Cacheable(key = "'category '+#p0.offset + '-' + #p0.pageSize ")
    public Page4Navigator<Category> list(Pageable pageable) {
        Page<Category> pageFromJPA = categoryDAO.findAll(pageable);
        Page4Navigator<Category> page = new Page4Navigator<>(pageFromJPA, 5);
        return page;
    }

    /**
     *
     假如是获取id=71的数据，那么
     就会以 key= "category 71" 到reids中去获取，如果没有就会从数据库中拿到，然后再以 key= "category 71" 这个值存放到 redis 当中。
     下一次再次访问的时候，根据这个key，就可以从 redis 里取到数据了。
     * @param id
     * @return
     */

    @Override
    @Cacheable(key="'category '+ #p0")
    public Category get(int id) {
        Category c = categoryDAO.getOne(id);
        //Category c =categoryDAO.findOne(id);
        return c;
    }

    @Override
    @CacheEvict(allEntries = true)
//  @CachePut(key="'category '+ #p0")
    public void save(Category category) {
        // TODO Auto-generated method stub
        categoryDAO.save(category);
    }

    @Override
    @CacheEvict(allEntries = true)
//  @CacheEvict(key="'category '+ #p0")
    public void delete(int id) {
        // TODO Auto-generated method stub

        Category one = categoryDAO.getOne(id);
        categoryDAO.delete(one);
    }

}
