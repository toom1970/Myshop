package com.ddd.movie.controller;

import com.ddd.movie.pojo.User;
import com.ddd.movie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Resource(name = "userService")
    UserService userService;

    @RequestMapping({"", "/"})
    public String index(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, HttpServletRequest request) {
        if (username == null && password == null)
            return "register";
        else {
            User user = new User(username, password);
            userService.add(user);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("rememberMe", true);
            return "forward:/login/user";
        }
    }
}
