package com.ddd.movie.service.serviceImpl;

import com.ddd.movie.dao.CinemaDao;
import com.ddd.movie.mapper.CinemaMapper;
import com.ddd.movie.pojo.Cinema;
import com.ddd.movie.service.CinemaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cinemaService")
public class CinemaServiceImpl implements CinemaService {
    @Resource(name = "cinemaDao")
    CinemaDao cinemaDao;
    @Resource(name = "cinemaMapper")
    CinemaMapper cinemaMapper;

    @Override
    public PageInfo findPageByMybatis(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Cinema> pagelist = cinemaMapper.findAll();
        return new PageInfo<>(pagelist);
    }

    @Override
    public Cinema findById(int id) {
        return cinemaDao.findById(id).orElse(new Cinema());
    }

    @Override
    public Cinema findByName(String name) {
        return cinemaDao.findByName(name);
    }

    @Override
    public List<Cinema> findAll() {
        return cinemaDao.findAll();
    }

    @Override
    public int add(Cinema cinema) {
        Cinema saved = cinemaDao.saveAndFlush(cinema);
        return saved.getId();
    }

    @Override
    public int delete(Cinema cinema) {
        cinemaDao.delete(cinema);
        return 0;
    }

    @Override
    public int update(Cinema cinema) {
        Cinema saved = cinemaDao.saveAndFlush(cinema);
        return saved.getId();
    }
}
