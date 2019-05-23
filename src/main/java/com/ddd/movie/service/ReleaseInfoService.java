package com.ddd.movie.service;

import com.ddd.movie.pojo.ReleaseInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ReleaseInfoService {
    PageInfo<ReleaseInfo> findPageByMybatis(Integer page, Integer size);

    ReleaseInfo findById(int id);

    List<ReleaseInfo> findByMovieIdAndCinemaId(int mid, int cid);
}
