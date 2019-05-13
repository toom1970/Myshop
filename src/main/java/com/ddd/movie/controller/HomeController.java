package com.ddd.movie.controller;

import com.ddd.movie.pojo.Tag;
import com.ddd.movie.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class HomeController {
    private Logger logger = Logger.getLogger("HomeController");

    @Resource(name = "tagService")
    TagService tagService;

    //    @ResponseBody
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        List<Tag> allTag = tagService.findAll();
        logger.info(allTag.toString());
        model.addAttribute("allCategory", allTag);
        return "home";
    }
}