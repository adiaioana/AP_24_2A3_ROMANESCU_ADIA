package org.example.firstdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/{[^\\.]*}")
    public String forward() {
        return "forward:/index.html";
    }
}
