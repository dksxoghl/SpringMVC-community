package com.taehi.springfirst.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class ExamController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/welcome")
    public String welcome(Model model) {
        System.out.println("welcome");
        Date today = new Date();
        model.addAttribute("today", today);
        return "welcome";
    }
}