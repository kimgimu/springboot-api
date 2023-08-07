package com.example.openAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MimiController {

    @GetMapping("/mimi/{pageNo}")
    public String mimi(@PathVariable int pageNo, Model model){
        model.addAttribute("pageNo",pageNo);
        System.out.println(pageNo);
        return "mimi";
    }

}
