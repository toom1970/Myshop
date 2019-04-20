package com.shop.myshop.controller;

import com.shop.myshop.dao.CategoryDao;
import com.shop.myshop.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    CategoryDao categoryDao;

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        Category newCategory = new Category("jerry");
        categoryDao.save(newCategory);
        return "hello";
    }

    @RequestMapping("/index1")
    public String indexHtml() {
        return "index";
    }
}
