package com.fatec.campeonatopaulista.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("")
@Controller
public class AppController {

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("redirect:/grupos");
    }
}
