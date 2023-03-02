package org.perscholas.studentcrm.controller;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.perscholas.studentcrm.model.MyUser;
import org.perscholas.studentcrm.service.ImageService;
import org.perscholas.studentcrm.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final MyUserRepoI myUserRepoI;
    private final MyUserService myUserService;
    private final ImageService imageService;

    @Autowired
    public UserController(MyUserRepoI myUserRepoI, MyUserService myUserService, ImageService imageService) {
        this.myUserRepoI = myUserRepoI;
        this.myUserService = myUserService;
        this.imageService = imageService;
    }

    @GetMapping("/form")
    public String userForm(Model model){
        model.addAttribute("user", new MyUser());

        return "form";
    }

    @PostMapping("/form/processing")
    public String updateOrCreateUser(@ModelAttribute("user") MyUser user, Model model, @RequestParam("file") MultipartFile file) throws Exception {
            MyUser fromDB = myUserService.createOrUpdate(user);
            model.addAttribute("user",fromDB);
            model.addAttribute("message", "success");
            imageService.save(file, fromDB.getEmail());



        return "form";
    }


}
