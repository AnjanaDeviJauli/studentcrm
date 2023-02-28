package org.perscholas.studentcrm.service;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.perscholas.studentcrm.data.CourseRepoI;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.perscholas.studentcrm.model.Course;
import org.perscholas.studentcrm.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service

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

}
