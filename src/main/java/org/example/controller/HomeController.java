package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;

@Controller
public class HomeController {

    @PermitAll
    @GetMapping("/")
    public ModelAndView openHomePage(){
        return new ModelAndView("home-page");
    }

//    @PermitAll
//    @GetMapping("/")
//    public String openHomePage(){
//        return "home-page";
//    }
}
