package org.perscholas.studentcrm.service;

import jakarta.transaction.Transactional;
import org.perscholas.studentcrm.data.CourseRepoI;
import org.perscholas.studentcrm.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class CourseService {

    CourseRepoI courseRepoI;

    @Autowired
    public CourseService(CourseRepoI courseRepoI) {
        this.courseRepoI = courseRepoI;
    }

    public void deleteCourse(int id) throws Exception {
        Optional<Course> wantToDelete = courseRepoI.findById(id);
        if(wantToDelete.isPresent())
            courseRepoI.delete(wantToDelete.get());
        else
            throw new Exception("Can't find the course" + wantToDelete);
    }
}
