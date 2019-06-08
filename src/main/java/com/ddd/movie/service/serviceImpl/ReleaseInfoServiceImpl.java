package com.ddd.movie.service.serviceImpl;

import com.ddd.movie.mapper.ReleaseInfoMapper;
import com.ddd.movie.pojo.ReleaseInfo;
import com.ddd.movie.service.ReleaseInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("releaseInfoService")
public class ReleaseInfoServiceImpl implements ReleaseInfoService {
    @Resource(name = "releaseInfoMapper")
    ReleaseInfoMapper releaseInfoMapper;

    @Override
    public PageInfo<ReleaseInfo> findPageByMybatis(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<ReleaseInfo> pagelist = releaseInfoMapper.findAll();
        return new PageInfo<>(pagelist);
    }

    @Override
    public ReleaseInfo findById(int id) {
        return releaseInfoMapper.findById(id);
    }

    @Override
    public List<ReleaseInfo> findByMovieIdAndCinemaId(int mid, int cid) {
        return releaseInfoMapper.findByMovieIdAndCinemaId(mid, cid);
    }

    @Override
    public List<ReleaseInfo> findByCinemaId(int cid) {
        return releaseInfoMapper.findByCinemaId(cid);
    }
}
