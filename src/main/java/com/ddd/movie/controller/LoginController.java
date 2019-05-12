package com.ddd.movie.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/")
//    @ResponseBody
    public String index(Model model) {
        model.addAttribute("Login", "asd");
        return "login";
    }

    @RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password",required = false) String userpwd, Model model) {
        if (username == null && userpwd == null) {
            model.addAttribute("Login", "null");
            return "login";
        } else {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated() == false) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, userpwd);
                try {
                    subject.login(token);
                    model.addAttribute("Login", "Login Success");
//                    subject.logout();
                } catch (UnknownAccountException e) {
                    model.addAttribute("LoginMessage", e.getMessage());
                    return "login";
                } catch (IncorrectCredentialsException e) {
                    model.addAttribute("LoginMessage", e.getMessage());
                    return "login";
                } catch (LockedAccountException e) {
                    model.addAttribute("LoginMessage", e.getMessage());
                    return "login";
                } catch (AuthenticationException e) {
                    model.addAttribute("LoginMessage", e.getMessage());
                    return "login";
                }
            }
        }
        return "login";
    }
}
