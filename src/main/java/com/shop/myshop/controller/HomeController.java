package com.shop.myshop.controller;

import com.mysql.cj.util.LogUtils;
import com.shop.myshop.dao.CategoryDao;
import com.shop.myshop.pojo.Category;
import com.shop.myshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class HomeController {
    Logger logger = Logger.getLogger("HomeController");

    @Autowired
    CategoryService categoryService;

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

}
