package org.perscholas.studentcrm.data;

import jakarta.transaction.Transactional;
import org.perscholas.studentcrm.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository @Transactional(rollbackOn = Exception.class)
public interface CourseRepoI extends JpaRepository<Course, Integer> {

    Optional<Course> findByNameAllIgnoreCase(String name);


}
