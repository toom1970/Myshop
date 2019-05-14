package com.ddd.movie.controller;

import com.ddd.movie.pojo.Movie;
import com.ddd.movie.service.MovieService;
import com.ddd.movie.service.serviceImpl.RestPageImpl;
import com.google.gson.Gson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Controller
public class HomeController {
    @Resource(name = "movieService")
    MovieService movieService;
    private Logger logger = Logger.getLogger("HomeController");

    //    @ResponseBody
    @RequestMapping({"", "/"})
    public String index(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, Model model) {
//        List<Movie> movies = movieService.findAll();
//        model.addAttribute("movies", movies);
        Page<Movie> pages = movieService.findJpa(page, 5);
        logger.info(pages.toString());
        model.addAttribute("movies", pages);
        return "home";
//        Gson gson = new Gson();
//        return gson.toJson(pages);
    }
}