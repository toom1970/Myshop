package com.ddd.movie.service.serviceImpl;

import com.ddd.movie.dao.MovieDao;
import com.ddd.movie.mapper.MovieMapper;
import com.ddd.movie.pojo.Movie;
import com.ddd.movie.service.MovieService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("movieService")
@CacheConfig(cacheNames = "movie")
public class MovieServiceImpl implements MovieService {
    @Resource(name = "movieDao")
    MovieDao movieDao;
    @Resource(name = "movieMapper")
    MovieMapper movieMapper;

    @Override
    @Cacheable(key = "'page'+#page")
    public PageInfo findAllMybatis(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Movie> pagelist = movieMapper.findAll();
        return new PageInfo<>(pagelist);
    }

    @Override
    @Cacheable(key = "'all'")
    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    @Override
    @Cacheable(key = "#id")
    public Movie findById(int id) {
        return movieDao.findById(id).orElse(new Movie());
    }

    @Override
    @Cacheable(key = "#name")
    public Movie findByName(String name) {
        return findByName(name);
    }

    @Override
    @CacheEvict(allEntries = true)
    public int add(Movie movie) {
        Movie saved = movieDao.saveAndFlush(movie);
        return saved.getId();
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(key = "#movie.getId()"),
//            @CacheEvict(key = "#movie.getName()"),
//            @CacheEvict(key = "'all'")})
    @CacheEvict(allEntries = true)
    public int delete(Movie movie) {
        movieDao.delete(movie);
        return 0;
    }

    @Override
    @CacheEvict(allEntries = true)
    public int update(Movie movie) {
        Movie saved = movieDao.saveAndFlush(movie);
        return saved.getId();
    }

    @Override
    public Page<Movie> findJpa(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size)
            size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        Page<Movie> pages = movieDao.findAll(pageable);
        return pages;
    }
}
