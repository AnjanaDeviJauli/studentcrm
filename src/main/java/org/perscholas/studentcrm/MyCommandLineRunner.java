package org.perscholas.studentcrm;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.studentcrm.data.CourseRepoI;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.perscholas.studentcrm.model.Course;
import org.perscholas.studentcrm.model.MyUser;
import org.perscholas.studentcrm.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {

    @PostConstruct
    public void init(){
        log.debug("======== My Command Line Runner =========");
    }


    MyUserRepoI myUserRepoI;
    CourseRepoI courseRepoI;
    MyUserService myUserService;

    @Autowired
    public MyCommandLineRunner(MyUserRepoI myUserRepoI, CourseRepoI courseRepoI, MyUserService myUserService) {
        this.myUserRepoI = myUserRepoI;
        this.courseRepoI = courseRepoI;
        this.myUserService = myUserService;
    }

    @Override
    public void run(String... args) throws Exception {

        myUserRepoI.saveAndFlush(
                new MyUser("jafer", "alhaboubi","jafer@gmail.com", "password"));
        myUserRepoI.saveAndFlush(
                new MyUser("Seckin", "Dogaroglu","seckin@gmail.com", "password"));
        courseRepoI.save(new Course("Java", "seckin@gmail.com"));

        myUserService.saveCourseToUser("jafer@gmail.com","Java");

    }
}
