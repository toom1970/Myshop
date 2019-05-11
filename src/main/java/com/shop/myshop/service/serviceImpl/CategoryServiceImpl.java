package com.shop.myshop.service.serviceImpl;

import com.shop.myshop.dao.CategoryDao;
import com.shop.myshop.pojo.Category;
import com.shop.myshop.service.CategoryService;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "category")
public class CategoryServiceImpl implements CategoryService {
    @Resource(name = "categoryDao")
    CategoryDao categoryDao;

    @Override
    @Cacheable(key = "'All'")
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    @Cacheable(key = "#id")
    public Category getById(int id) {
        return categoryDao.getOne(id);
    }

    @Override
    @Cacheable(key = "#name")
    public Category findByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
//    @CacheEvict(allEntries = true)
    public int add(Category category) {
        Category saved = categoryDao.saveAndFlush(category);
        return saved.getId();
    }

    @Override
    @CacheEvict(key = "#category.getId()")
    public int delete(Category category) {
        categoryDao.delete(category);
        return 0;
    }

    @Override
    @CachePut(key = "#category.getId()")
    public int update(Category category) {
        Category saved = categoryDao.saveAndFlush(category);
        return saved.getId();
    }
}
