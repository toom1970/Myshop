package com.shop.myshop.controller;

import com.shop.myshop.dao.UserDao;
import com.shop.myshop.pojo.Category;
import com.shop.myshop.pojo.User;
import com.shop.myshop.service.CategoryService;
import com.shop.myshop.shiroRealm.ShiroSimpleByteSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class HomeController {
    Logger logger = Logger.getLogger("HomeController");

    @Autowired
    CategoryService categoryService;
    @Autowired
    UserDao userDao;

    //    @ResponseBody
    @RequestMapping("/")
    public String index(Model model) {
//        Category newCategory = new Category("yura");
//        categoryDao.save(newCategory);
//        model.addAttribute("name", newCategory.getName());
        List<Category> allCategory = categoryService.findAll();
        logger.info(allCategory.toString());
        model.addAttribute("allCategory", allCategory);
        return "home";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("userpwd") String userpwd, Model model) {
        if (username == null && userpwd == null) {
//        if (username.equals("bbb")) {
//            String hashAlgorithmName = "MD5";
//            Object result = new SimpleHash(hashAlgorithmName, userpwd, new ShiroSimpleByteSource(username), 2);
//            String saltpwd = result.toString();
//            User user = new User();
//            user.setName(username);
//            user.setPassword(saltpwd);
//            user.setSalt(username);
//            userDao.save(user);
//        }
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
////            return "redirect:/";
        }
        return "login";
        //    }
    }
}
