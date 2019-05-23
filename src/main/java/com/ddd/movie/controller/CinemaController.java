package com.ddd.movie.controller;

import com.ddd.movie.pojo.Cinema;
import com.ddd.movie.service.CinemaService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/cinema")
public class CinemaController {
    @Resource(name = "cinemaService")
    CinemaService cinemaService;
    Gson gson = new Gson();

    @RequestMapping({"", "/"})
//    @ResponseBody
    public String index(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page, Model model) {
        PageInfo pageInfo = cinemaService.findPageByMybatis(page, 5);
        List<Cinema> cinemaList = pageInfo.getList();
        model.addAttribute("cinemas", cinemaList);
        model.addAttribute("pages", pageInfo);
        System.out.println(cinemaList.get(1).getService());
//        return gson.toJson(cinemaList);
        return "cinemaHome";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT,RequestMethod.GET})
    public String editCinema(@PathVariable("id") int id, @RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "area", required = false) String area, @RequestParam(name = "contactNumber", required = false) String contactNumber,
                             @RequestParam(name = "service", required = false) String service, Model model) {
        Cinema cinema = cinemaService.findById(id);
        if (name == null && area == null && contactNumber == null && service == null) {
            model.addAttribute("cinema", cinema);
            return "editCinema";
        } else {
            if (!name.equals(""))
                cinema.setName(name);
            if (!area.equals(""))
                cinema.setArea(area);
            if (!contactNumber.equals(""))
                cinema.setContactNumber(contactNumber);
            if (!service.equals(""))
                cinema.setService(service);
            cinemaService.update(cinema);
            return "redirect:/cinema";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delCinema(@PathVariable("id") int id) {
        Cinema cinema = cinemaService.findById(id);
        if (cinema != null)
            cinemaService.delete(cinema);
        return "redirect:/cinema";
    }

    @RequestMapping(value = "/add")
    public String addCinema(@RequestParam(name = "name", required = false) String name,
                            @RequestParam(name = "area", required = false) String area, @RequestParam(name = "contactNumber", required = false) String contactNumber,
                            @RequestParam(name = "service", required = false) String service) {
        if (name == null && area == null && contactNumber == null && service == null) {
            return "addCinema";
        } else {
            Cinema cinema = new Cinema(name, area, contactNumber, service);
            cinemaService.add(cinema);
            return "redirect:/cinema";
        }
    }
}
