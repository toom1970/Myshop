package com.ddd.movie.controller;

import com.ddd.movie.service.MovieService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Controller
public class HomeController {
    @Resource(name = "movieService")
    MovieService movieService;
    private Logger logger = Logger.getLogger("HomeController");

    //    @ResponseBody
    @RequestMapping({"", "/"})
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model) {
        PageInfo pagesList = movieService.findAllMybatis(page, 5);
        model.addAttribute("movies", pagesList.getList());
        model.addAttribute("pages", pagesList);
        return "home";
    }
}