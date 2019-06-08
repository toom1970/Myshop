package com.ddd.movie.controller;

import com.ddd.movie.pojo.Movie;
import com.ddd.movie.service.MovieService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("")
public class MovieUserController {
    @Resource(name = "movieService")
    MovieService movieService;

    @RequestMapping({"", "/"})
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model) {
        String url = "https://api.douban.com/v2/movie/subject/1866479?apikey=0df993c66c0c636e29ecbb5344252a4a";
//        List<Movie> movies = movieService.findAll(url);
        PageInfo pageinfo = movieService.findPageByMybatis(page, 20);
        model.addAttribute("movies", pageinfo.getList());
        model.addAttribute("pages", pageinfo);
//        model.addAttribute("movies", movies);
        model.addAttribute("page", page);
        model.addAttribute("nextPage", page + 1);
        return "movie";
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String browseMovie(@PathVariable("id") int id, Model model) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        return "movieDetails";
    }
}
