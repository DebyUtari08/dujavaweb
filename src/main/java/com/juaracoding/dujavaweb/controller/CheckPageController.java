package com.juaracoding.dujavaweb.controller;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
IntelliJ IDEA 2022.3.2 (Community Edition)
Build #IC-223.8617.56, built on January 26, 2023
@Author USER a.k.a. Deby Utari
Java Developer
Created on 27/02/2023 23:36
@Last Modified 27/02/2023 23:36
Version 1.0
*/

import com.juaracoding.dujavaweb.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/check")
public class CheckPageController {

    @GetMapping("/signin")
    public String pageTwo(Model model)
    {
        User users = new User();
        model.addAttribute("users",users);
        return "signin";
    }

    @GetMapping("/register")
    public String pageOne(Model model)
    {
        User users = new User();
        model.addAttribute("users",users);
        return "register";
    }

    @GetMapping("/verify")
    public String pageTwor(Model model)
    {
        model.addAttribute("email", "Paulo");
        return "verify";
    }
    @GetMapping("/index1")
    public String pageThree()
    {
        return "index_1";

    }
    @GetMapping("/styledPage")
    public String pageFour(Model model)
    {
        model.addAttribute("name", "Paulo");
        return "styledPage";
    }

    @GetMapping("/index")
    public String pageFive()
    {
        return "index";

    }



}

