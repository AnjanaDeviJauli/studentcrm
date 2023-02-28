package org.perscholas.studentcrm.controller;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    MyUserRepoI myUserRepoI;

    @Autowired
    public AdminController(MyUserRepoI myUserRepoI) {
        this.myUserRepoI = myUserRepoI;
    }

    @GetMapping("allusers")
    public String allUsers(Model model){
        model.addAttribute("myUser",myUserRepoI.findAll());
        log.debug(myUserRepoI.findAll().toString());

        return "index";
    }
    @GetMapping("allcourses")
    public String allCourses(){

        return "index";
    }
}
