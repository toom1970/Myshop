package com.ddd.movie.controller;

import com.ddd.movie.pojo.Movie;
import com.ddd.movie.service.MovieService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class MovieUserController {
    @Resource(name = "movieService")
    MovieService movieService;

    @RequestMapping({"", "/"})
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model) {
        int pageN = movieService.MoviePageNum("http://127.0.0.1:5000/movieOnInfoList?offset=12");
        List<Movie> movies;
        PageInfo pageInfo = new PageInfo();
        if (page <= pageN) {
            movies = movieService.findAll(page);
        } else {
            pageInfo = movieService.findPageByMybatis(page - pageN, 12);
            movies = pageInfo.getList();
        }
        model.addAttribute("movies", movies);
        model.addAttribute("page", page);
        model.addAttribute("pages", pageInfo);
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("isLogin", subject.isAuthenticated());
        return "movie";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String browseMovie(@PathVariable("id") int id, Model model) {
        Movie movie = null;
        movie = movieService.findById(id);
        if (movie == null)
            movie = movieService.findByIdJson(id);
        if (movie == null)
            movie = new Movie();
        model.addAttribute("movie", movie);
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("isLogin", subject.isAuthenticated());
        return "movieDetails";
    }
}
