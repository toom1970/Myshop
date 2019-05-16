package com.ddd.movie.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping({"", "/"})
//    @ResponseBody
    public String index(Model model) {
        model.addAttribute("loginMessage", "plz login first");
        return "login";
    }

    @RequestMapping(value = "/user", method = {RequestMethod.POST})
    public String login(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String userpwd, Model model) {
        if (username == null && userpwd == null) {
            model.addAttribute("loginMessage", "nothing input");
            return "login";
        } else {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated() == false) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, userpwd);
                try {
                    subject.login(token);
//                    model.addAttribute("loginMessage", "Login Success");
                } catch (UnknownAccountException e) {
                    model.addAttribute("loginMessage", e.getMessage());
                    return "login";
                } catch (IncorrectCredentialsException e) {
                    model.addAttribute("loginMessage", e.getMessage());
                    return "login";
                } catch (LockedAccountException e) {
                    model.addAttribute("loginMessage", e.getMessage());
                    return "login";
                } catch (AuthenticationException e) {
                    model.addAttribute("loginMessage", e.getMessage());
                    return "login";
                }
            }
            return "redirect:/";
        }
    }
}
