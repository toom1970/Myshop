package com.ddd.movie.controller;

import com.ddd.movie.dao.AreaDao;
import com.ddd.movie.mapper.AreaMapper;
import com.ddd.movie.mapper.ReleaseInfoMapper;
import com.ddd.movie.pojo.Area;
import com.ddd.movie.pojo.Cinema;
import com.ddd.movie.pojo.Movie;
import com.ddd.movie.pojo.ReleaseInfo;
import com.ddd.movie.service.CinemaService;
import com.ddd.movie.service.MovieService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manage/cinema")
public class CinemaController {
    @Resource(name = "cinemaService")
    CinemaService cinemaService;
    @Resource(name = "releaseInfoMapper")
    ReleaseInfoMapper releaseInfoMapper;
    @Resource(name = "movieService")
    MovieService movieService;
    Gson gson = new Gson();
    @Autowired
    AreaDao areaDao;

    @RequestMapping({"", "/"})
//    @ResponseBody
    public String index(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page, Model model) {
        PageInfo pageInfo = cinemaService.findPageByMybatis(page, 15);
        List<Cinema> cinemaList = pageInfo.getList();
        model.addAttribute("cinemas", cinemaList);
        model.addAttribute("pages", pageInfo);
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("isLogin", subject.isAuthenticated());
//        return gson.toJson(cinemaList);
        return "cinemaHome";
    }

    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String editCinema(@PathVariable("id") int id, @RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "area", required = false) String area,
                             @RequestParam(name = "contactNumber", required = false) String contactNumber,
                             @RequestParam(name = "service", required = false) String service, Model model) {
        Cinema cinema = cinemaService.findById(id);
        if (name == null && area == null && contactNumber == null && service == null) {
            model.addAttribute("cinema", cinema);
            List<ReleaseInfo> releaseInfos = releaseInfoMapper.findByCinemaId(id);
            model.addAttribute("releases", releaseInfos);
            return "editCinema";
        } else {
            if (!name.equals(""))
                cinema.setName(name);
            if (!area.equals("")) {
                Area a = cinema.getArea();
                a.setName(area);
                cinema.setArea(a);
            }
            if (!contactNumber.equals(""))
                cinema.setContactNumber(contactNumber);
            if (!service.equals(""))
                cinema.setService(service);
            cinemaService.update(cinema);
            return "redirect:/manage/cinema";
        }
    }

    @RequestMapping(value = "/del/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delCinema(@PathVariable("id") int id) {
        Cinema cinema = cinemaService.findById(id);
        if (cinema != null)
            cinemaService.delete(cinema);
        return "redirect:/manage/cinema";
    }

    @RequestMapping(value = "/add")
    public String addCinema(@RequestParam(name = "name", required = false) String name,
                            @RequestParam(name = "area", required = false) String area,
                            @RequestParam(name = "contactNumber", required = false) String contactNumber,
                            @RequestParam(name = "service", required = false) String service) {
        if (name == null && area == null && contactNumber == null && service == null) {
            return "addCinema";
        } else {
            Cinema cinema = new Cinema(name, contactNumber, service);
            int id = cinemaService.add(cinema);
            Area carea = new Area(area, id);
            cinema.setArea(carea);
            areaDao.save(carea);
            return "redirect:/manage/cinema";
        }
    }

    @RequestMapping(value = "/delRel/{id}", method = {RequestMethod.GET})
    public String delCinema(@PathVariable("id") int id, @RequestParam(name = "cid") int cid) {
        ReleaseInfo releaseInfo = releaseInfoMapper.findById(id);
        if (releaseInfo != null)
            releaseInfoMapper.delete(id);
        return "redirect:/manage/cinema/edit/" + cid;
    }

    @RequestMapping(value = "/add/release")
    public String addReleaseMovie(@RequestParam(name = "name", required = false) String name,
                                  @RequestParam(name = "cid", required = false) Integer cid,
                                  @RequestParam(name = "date", required = false) String date,
                                  @RequestParam(name = "time", required = false) String time,
                                  @RequestParam(name = "type", required = false) String type,
                                  @RequestParam(name = "lang", required = false) String lang,
                                  @RequestParam(name = "th", required = false) String th,
                                  @RequestParam(name = "price", required = false) Double price,
                                  Model model) {
        if (name == null && date == null && type == null && lang == null && price == null && th == null && time == null) {
            model.addAttribute("cid", cid);
            return "addRelease";
        } else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date nDate = simpleDateFormat.parse(date+" "+time);
                ReleaseInfo releaseInfo;
                if (price != null)
                    releaseInfo = new ReleaseInfo(cinemaService.findById(cid), nDate, price, type, th, lang);
                else
                    releaseInfo = new ReleaseInfo(cinemaService.findById(cid), nDate, 0, type, th, lang);
                Movie movie = movieService.findByName(name);
                if (movie == null) {
                    model.addAttribute("msg", "Movie not exists");
                    return "addRelease";
                }
                model.addAttribute("cid", cid);
                releaseInfo.setMovie(movie);
                releaseInfoMapper.add(releaseInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/manage/cinema/edit/" + cid;
        }
    }
}
