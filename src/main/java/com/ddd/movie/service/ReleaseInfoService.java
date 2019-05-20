package com.ddd.movie.service;

import com.ddd.movie.pojo.ReleaseInfo;
import com.github.pagehelper.PageInfo;

public interface ReleaseInfoService {
    PageInfo<ReleaseInfo> findPageByMybatis(Integer page, Integer size);

    ReleaseInfo findById(int id);

}
