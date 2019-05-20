package com.ddd.movie.controller;

import com.ddd.movie.pojo.Cinema;
import com.ddd.movie.service.CinemaService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/cinema")
public class CinemaController {
    @Resource(name = "cinemaService")
    CinemaService cinemaService;
    Gson gson = new Gson();

    @RequestMapping({"", "/"})
    @ResponseBody
    public String index(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
        PageInfo pageInfo = cinemaService.findPageByMybatis(page, 5);
        List<Cinema> cinemaList = pageInfo.getList();
        return gson.toJson(cinemaList);
    }

}
