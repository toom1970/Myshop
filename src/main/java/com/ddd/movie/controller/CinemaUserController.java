package com.ddd.movie.controller;

import com.ddd.movie.pojo.Cinema;
import com.ddd.movie.pojo.Movie;
import com.ddd.movie.pojo.ReleaseInfo;
import com.ddd.movie.service.CinemaService;
import com.ddd.movie.service.ReleaseInfoService;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/cinema")
public class CinemaUserController {
    @Resource(name = "cinemaService")
    CinemaService cinemaService;
    @Resource(name = "releaseInfoService")
    ReleaseInfoService releaseInfoService;

    @RequestMapping({"", "/"})
    public String index(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page, Model model) {
        PageInfo pageInfo = cinemaService.findPageByMybatis(page, 5);
        List<Cinema> cinemaList = pageInfo.getList();
        model.addAttribute("cinemas", cinemaList);
        model.addAttribute("pages", pageInfo);
        return "cinema";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String editCinema(@PathVariable("id") int id, Model model) {
        Cinema cinema = cinemaService.findById(id);
        List<ReleaseInfo> releaseInfos = releaseInfoService.findByCinemaId(id);
        List<Movie> movies = new ArrayList<>();
        for (ReleaseInfo r : releaseInfos) {
            movies.add(r.getMovie());
        }
        model.addAttribute("releaseMovie", movies);
        model.addAttribute("cinema", cinema);
        return "cinemaDetails";
    }
}
