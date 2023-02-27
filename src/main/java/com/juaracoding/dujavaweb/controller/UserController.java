package com.juaracoding.dujavaweb.controller;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
IntelliJ IDEA 2022.3.2 (Community Edition)
Build #IC-223.8617.56, built on January 26, 2023
@Author USER a.k.a. Deby Utari
Java Developer
Created on 27/02/2023 23:40
@Last Modified 27/02/2023 23:40
Version 1.0
*/

import com.juaracoding.dujavaweb.model.User;
import com.juaracoding.dujavaweb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/authz")
public class UserController {

    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;
    private Map<String,Object> objectMapper = new HashMap<String,Object>();

    private List<User> lsCPUpload = new ArrayList<User>();

    private String [] strExceptionArr = new String[2];

    @Autowired
    public UserController(UserService userService) {
        strExceptionArr[0]="UserController";
        this.userService = userService;
    }

    @PostMapping("/v1/register")
    public String doRegis(@ModelAttribute("users") User userz,Model model) throws Exception {
        String strEmail = userService.checkRegis(userz);

        if(!strEmail.equals(""))
        {
            model.addAttribute("verifyEmail",strEmail);
            return "verfikasi";
//            return "redirect:/api/check/verify";
        }
        else
        {
            return "redirect:/api/check/signin";
        }
    }

//    @PostMapping("/v1/verify")
//    public String verifyRegis()
//    {
//
//
//    }
}
