package org.perscholas.studentcrm.controller;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.studentcrm.data.CourseRepoI;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.perscholas.studentcrm.model.Course;
import org.perscholas.studentcrm.model.MyUser;
import org.perscholas.studentcrm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private final CourseRepoI courseRepoI;

    MyUserRepoI myUserRepoI;
    CourseService courseService;

    @Autowired
    public AdminController(CourseRepoI courseRepoI, MyUserRepoI myUserRepoI, CourseService courseService) {
        this.courseRepoI = courseRepoI;
        this.myUserRepoI = myUserRepoI;
        this.courseService = courseService;
    }

    @GetMapping("dashboard")
    public String dash(Model model){
        List<MyUser> allUsers = myUserRepoI.findAll();
        model.addAttribute("myUser", allUsers);
        List<Course> allCourses = courseRepoI.findAll();
        model.addAttribute("allCourses",allCourses);
        log.debug(allCourses.toString());
        log.debug(allUsers.toString());

        return "index";
    }



    @GetMapping("/deletecourse/{id}")
    public String deleteCourse(@PathVariable(name = "id") int id) throws Exception {
        log.debug("Value of the string" + String.valueOf(id));
       courseService.deleteCourse(id);
        return "redirect:/admin/dashboard";
    }

}
