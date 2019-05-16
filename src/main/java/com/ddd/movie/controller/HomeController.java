package com.ddd.movie.controller;

import com.ddd.movie.pojo.Movie;
import com.ddd.movie.service.MovieService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
//import java.sql.Date;
import java.util.Date;
import java.util.logging.Logger;

@Controller
public class HomeController {
    @Resource(name = "movieService")
    MovieService movieService;
    Gson gson = new Gson();
    private Logger logger = Logger.getLogger("HomeController");

    //    @ResponseBody
    @RequestMapping({"", "/"})
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model) {
        PageInfo pagesList = movieService.findAllMybatis(page, 5);
        model.addAttribute("movies", pagesList.getList());
        model.addAttribute("pages", pagesList);
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("isLogin", subject.isAuthenticated());
        return "home";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    @ResponseBody
    public String editMovie(@PathVariable("id") int id, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "director", required = false) String director, @RequestParam(value = "releasetime", required = false) Date releaseTime, Model model) {
        if (name == null && director == null && releaseTime == null) {
            Movie movie = movieService.findById(id);
            model.addAttribute("movie", movie);
//        return gson.toJson(movie);
            return "editMovie";
        } else {
            return "redirect:/";
        }
    }
}