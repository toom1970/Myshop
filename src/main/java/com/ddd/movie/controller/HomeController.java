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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public String editMovie(@PathVariable("id") int id, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "director", required = false) String director, @RequestParam(value = "releasetime", required = false) String releaseTime, Model model) {
        Movie movie = movieService.findById(id);
        if (name == null && director == null && releaseTime == null) {
//            Movie movie = movieService.findById(id);
            model.addAttribute("movie", movie);
//        return gson.toJson(movie);
            return "editMovie";
        } else {
            if (!name.equals(""))
                movie.setName(name);
            if (!director.equals(""))
                movie.setDirector(director);
            if (!releaseTime.equals("")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    movie.setReleaseDate(dateFormat.parse(releaseTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            movieService.update(movie);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/del/{id}")
    public String delMovie(@PathVariable("id") int id) {
        Movie movie = movieService.findById(id);
        if (movie != null)
            movieService.delete(movie);
        return "redirect:/";
    }

    @RequestMapping(value = "/add/{id}")
    public String addMovie(Movie movie) {
        movieService.add(movie);
        return "redirect:/";
    }

    @RequestMapping(value = "/add")
    public String addMoviePage() {
        return "addPage";
    }
}