package com.ddd.movie.controller;

import com.ddd.movie.pojo.Cinema;
import com.ddd.movie.pojo.Movie;
import com.ddd.movie.pojo.ReleaseInfo;
import com.ddd.movie.service.CinemaService;
import com.ddd.movie.service.ReleaseInfoService;
import com.ddd.movie.utils.JsonToObjectUtils;
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
@RequestMapping("/cinema")
public class CinemaUserController {
    @Resource(name = "cinemaService")
    CinemaService cinemaService;
    @Resource(name = "releaseInfoService")
    ReleaseInfoService releaseInfoService;

    @RequestMapping({"", "/"})
    public String index(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page, Model model) {
        List<Cinema> cinemaList;
        PageInfo pageInfo = new PageInfo();
        int pageN = cinemaService.cinemaPageNum("http://127.0.0.1:5000/cinemaList?offset=20&districtId=-1&lineId=-1&areaId=-1&stationId=-1&cityId=50");
        if (page <= pageN) {
            String url = "http://127.0.0.1:5000/cinemaList?offset=" + 20 * page + "&districtId=-1&lineId=-1&areaId=-1&stationId=-1&cityId=50";
            cinemaList = cinemaService.findAll(url);
        } else {
            pageInfo = cinemaService.findPageByMybatis(page - pageN, 20);
            cinemaList = pageInfo.getList();
        }
        model.addAttribute("pages", pageInfo);
        model.addAttribute("cinemas", cinemaList);
        model.addAttribute("page", page);
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("isLogin", subject.isAuthenticated());
        return "cinema";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public String editCinema(@PathVariable("id") int id, Model model) {
        Cinema cinema = null;
        List<Movie> movies = new ArrayList<>();
        List<ReleaseInfo> releaseInfos;
        cinema = cinemaService.findById(id);
        if (cinema != null) {
            releaseInfos = releaseInfoService.findByCinemaId(id);
            for (ReleaseInfo r : releaseInfos) {
                movies.add(r.getMovie());
            }
        } else {
            cinema = cinemaService.findByIdJson(id);
            movies = cinemaService.findReleaseMovie(id);
            releaseInfos = cinemaService.findReleaseInfo(id);
        }
        if (cinema == null)
            cinema = new Cinema();
        model.addAttribute("releaseMovie", movies);
        model.addAttribute("cinema", cinema);
        model.addAttribute("release", releaseInfos);
        return "cinemaDetails";
    }
}
