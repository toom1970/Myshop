package com.ddd.movie.controller;

import com.ddd.movie.pojo.User;
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
    public String index(Model model) {
        model.addAttribute("loginMessage", "Please login first");
        return "login";
    }

    @RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String userpwd, @RequestParam(value = "rememberMe", required = false) boolean rememberMe, Model model) {
        if (username == null && userpwd == null) {
            model.addAttribute("loginMessage", "nothing input");
            return "login";
        } else {
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, userpwd);
                token.setRememberMe(rememberMe);
                System.out.println(username + userpwd + rememberMe);
                try {
                    subject.login(token);
                } catch (UnknownAccountException e) {
                    model.addAttribute("loginMessage", e.getMessage());
                    return e.getMessage();
//                    return "login";
                } catch (IncorrectCredentialsException e) {
                    model.addAttribute("loginMessage", e.getMessage());
                    return e.getMessage();
//                    return "login";
                } catch (LockedAccountException e) {
                    model.addAttribute("loginMessage", e.getMessage());
                    return e.getMessage();
//                    return "login";
                } catch (AuthenticationException e) {
                    model.addAttribute("loginMessage", e.getMessage());
                    return e.getMessage();
//                    return "login";
                }
            }
            User user = (User) subject.getPrincipal();
            if (user.getRoles().isEmpty())
                return "redirect:/";
            else
                return "redirect:/manage/movie";
        }
    }
}
