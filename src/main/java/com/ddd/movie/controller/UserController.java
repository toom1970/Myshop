package com.ddd.movie.controller;

import com.ddd.movie.pojo.User;
import com.ddd.movie.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/manage/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping({"", "/"})
    public String index(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("cinemas", users);
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("isLogin", subject.isAuthenticated());
        return "user";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id") int id) {
        User user = userService.getById(id);
        if (user != null)
            userService.delete(user);
        return "redirect:/manage/user";
    }
}
