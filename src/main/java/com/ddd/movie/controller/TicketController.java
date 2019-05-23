package com.ddd.movie.controller;

import com.ddd.movie.pojo.Order;
import com.ddd.movie.pojo.ReleaseInfo;
import com.ddd.movie.pojo.User;
import com.ddd.movie.service.OrderService;
import com.ddd.movie.service.ReleaseInfoService;
import com.ddd.movie.service.UserService;
import com.ddd.movie.utils.OrderCodeFactory;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Resource(name = "releaseInfoService")
    ReleaseInfoService releaseInfoService;
    @Resource(name = "orderService")
    OrderService orderService;
    OrderCodeFactory orderCodeFactory = new OrderCodeFactory(0, 0);
    @Autowired
    Gson gson;

    @Resource(name = "userService")
    UserService userService;

    @RequestMapping(value = {"/{mid}/{cid}"}, method = RequestMethod.GET)
    @ResponseBody
    public String index(@PathVariable("mid") int movieId, @PathVariable("cid") int cinemaId, Model model) {
        List<ReleaseInfo> releaseInfos = releaseInfoService.findByMovieIdAndCinemaId(movieId, cinemaId);
        return gson.toJson(releaseInfos);
    }

    @RequestMapping(value = "/order/{id}")
    @ResponseBody
    public String order(@PathVariable("id") int id, @RequestParam(name = "number", required = false) Integer number, Model model) {
        long orderId = orderCodeFactory.nextId();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        ReleaseInfo releaseInfo = releaseInfoService.findById(id);
        if (number == null) {
            model.addAttribute("orderId", orderId);
            model.addAttribute("releaseInfo", releaseInfo);
            return "orderPage";
        } else {
            Order order = new Order(orderId, releaseInfo, user, number, 1);
            Session session = subject.getSession();
            session.setAttribute("order", order);
            return gson.toJson(order);
        }
    }

    //0: Cancel  1: waiting 2: confirmed
    @RequestMapping("/order/confirm")
    @ResponseBody
    public String confirm(@RequestParam(name = "isConfirm", required = false) int isConfirm, Model model) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Order order = (Order) session.getAttribute("order");
        if (isConfirm == 1) {
            model.addAttribute("order", order);
            return "confirmPage";
        } else if (isConfirm == 2) {
            order.setStatus(2);
            orderService.add(order);
            session.removeAttribute("order");
            return "redirect:/";
        } else {
            int mid = order.getReleaseInfo().getMovie().getId();
            int cid = order.getReleaseInfo().getCinema().getId();
            session.removeAttribute("order");
            return "redirect:/ticket/" + mid + "/" + cid;
        }
    }
}
