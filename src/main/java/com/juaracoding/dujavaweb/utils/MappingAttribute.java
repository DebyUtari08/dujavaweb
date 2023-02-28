package com.juaracoding.dujavaweb.utils;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
IntelliJ IDEA 2022.3.2 (Community Edition)
Build #IC-223.8617.56, built on January 26, 2023
@Author USER a.k.a. Deby Utari
Java Developer
Created on 28/02/2023 21:03
@Last Modified 28/02/2023 21:03
Version 1.0
*/


import org.springframework.ui.Model;

import java.util.Date;
import java.util.Map;

public class MappingAttribute {

    public void setAttribute(Model model,Map<String,Object> mapz)
    {
        model.addAttribute("message", mapz.get("message"));
        model.addAttribute("status", mapz.get("status"));
        model.addAttribute("data", mapz.get("responseObj")==null?"":mapz.get("responseObj"));
        model.addAttribute("timestamp", new Date());
        model.addAttribute("success",mapz.get("success"));
        if(mapz.get("errorCode") != null)
        {
            model.addAttribute("errorCode",mapz.get("errorCode"));
            model.addAttribute("path",mapz.get("path"));
        }
    }
}

