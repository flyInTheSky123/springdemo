package com.person.springdemo.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

@Controller
public class hello {

    @RequestMapping("/hello")
    public String sayHello(Model model) throws Exception {
        model.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
        if (true){
            throw new Exception("some exception");
        }

        return "hello";

    }


}
