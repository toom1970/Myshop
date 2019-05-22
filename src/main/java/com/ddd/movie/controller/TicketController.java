package com.ddd.movie.controller;

import com.ddd.movie.pojo.ReleaseInfo;
import com.ddd.movie.service.ReleaseInfoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Resource(name = "releaseInfoService")
    ReleaseInfoService releaseInfoService;
    @Autowired
    Gson gson;

    @RequestMapping(value = {"", "/"},method = RequestMethod.GET)
    @ResponseBody
    public String index( Model model) {
//        PageInfo pageinfo = releaseInfoService.findPageByMybatis(page, 5);
        ReleaseInfo releaseInfo = releaseInfoService.findById(1);
        return gson.toJson(releaseInfo);
    }
}
