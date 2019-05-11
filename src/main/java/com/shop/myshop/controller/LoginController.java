package com.shop.myshop.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String userpwd, Model model) {
        if (username == null && userpwd == null) {
//            if (username.equals("bbb")) {
//            String hashAlgorithmName = "MD5";
//            Object result = new SimpleHash(hashAlgorithmName, userpwd, new ShiroSimpleByteSource(username), 2);
//            String saltpwd = result.toString();
//            User user = new User();
//            user.setName(username);
//            user.setPassword(saltpwd);
//            user.setSalt(username);
//            userDao.save(user);
//            }
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
