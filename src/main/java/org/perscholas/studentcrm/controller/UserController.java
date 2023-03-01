package org.perscholas.studentcrm.controller;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.perscholas.studentcrm.model.MyUser;
import org.perscholas.studentcrm.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final MyUserRepoI myUserRepoI;
    private final MyUserService myUserService;

    @Autowired
    public UserController(MyUserRepoI myUserRepoI, MyUserService myUserService) {
        this.myUserRepoI = myUserRepoI;
        this.myUserService = myUserService;
    }

    @GetMapping("/form")
    public String userForm(Model model){
        model.addAttribute("user", new MyUser());

        return "form";
    }

    @PostMapping("/form/processing")
    public String updateOrCreateUser(@ModelAttribute("user") MyUser user, Model model){
            model.addAttribute("user",myUserService.createOrUpdate(user));
            model.addAttribute("message", "success");
        return "form";
    }


}
