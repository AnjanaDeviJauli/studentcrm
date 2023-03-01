package org.perscholas.studentcrm.service;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.studentcrm.data.CourseRepoI;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.perscholas.studentcrm.model.Course;
import org.perscholas.studentcrm.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
public class MyUserService {

    MyUserRepoI myUserRepoI;
    CourseRepoI courseRepoI;

    @Autowired
    public MyUserService(MyUserRepoI myUserRepoI, CourseRepoI courseRepoI) {
        this.myUserRepoI = myUserRepoI;
        this.courseRepoI = courseRepoI;


    }

    public MyUser saveCourseToUser(String email, String courseName) throws Exception {

        if(myUserRepoI.findByEmailAllIgnoreCase(email).isPresent() && courseRepoI.findByNameAllIgnoreCase(courseName).isPresent()) {
            MyUser myUser = myUserRepoI.findByEmailAllIgnoreCase(email).get();
            Course course = courseRepoI.findByNameAllIgnoreCase(courseName).get();
            myUser.addCourse(course);
            return myUser;

        } else {
            throw new Exception("saving a course to the user " + email + " did not go well!!!!!");
        }

    }


    public MyUser createOrUpdate(MyUser user){
        if(myUserRepoI.findByEmailAllIgnoreCase(user.getEmail()).isPresent()) {
            log.debug("User email " + user.getEmail() + " exists!");
            MyUser originalUser = myUserRepoI.findByEmailAllIgnoreCase(user.getEmail()).get();
            originalUser.setFirstName(user.getFirstName());
            originalUser.setLastName(user.getLastName());
            originalUser.setPassword(user.getPassword());

            return myUserRepoI.save(originalUser);

        } else {
            log.debug("User email " + user.getEmail() + " do not exists!");
            return myUserRepoI.save(user);
        }

    }

}
