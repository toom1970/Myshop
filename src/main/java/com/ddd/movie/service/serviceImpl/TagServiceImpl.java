package com.ddd.movie.service.serviceImpl;

import com.ddd.movie.pojo.Tag;
import com.ddd.movie.service.TagService;
import com.ddd.movie.dao.TagDao;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "category")
public class TagServiceImpl implements TagService {
    @Resource(name = "tagDao")
    TagDao tagDao;

    @Override
    @Cacheable(key = "'All'")
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    @Cacheable(key = "#id")
    public Tag getById(int id) {
        return tagDao.getOne(id);
    }

    @Override
    @Cacheable(key = "#name")
    public Tag findByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
//    @CacheEvict(allEntries = true)
    public int add(Tag tag) {
        Tag saved = tagDao.saveAndFlush(tag);
        return saved.getId();
    }

    @Override
    @CacheEvict(key = "#tag.getId()")
    public int delete(Tag tag) {
        tagDao.delete(tag);
        return 0;
    }

    @Override
    @CachePut(key = "#tag.getId()")
    public int update(Tag tag) {
        Tag saved = tagDao.saveAndFlush(tag);
        return saved.getId();
    }
}
