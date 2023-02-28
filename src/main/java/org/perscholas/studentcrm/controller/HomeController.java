package org.perscholas.studentcrm.controller;


import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    public String homePage(){
        return "index";
    }

}
