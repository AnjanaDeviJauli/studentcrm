package org.perscholas.studentcrm.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    String name;

    @NonNull
    String instructorEmail;


    @ToString.Exclude
    @ManyToMany(mappedBy = "courses", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<MyUser> myUsers = new ArrayList<>();

    public void addUser(MyUser myUser){
        myUsers.add(myUser);
        myUser.getCourses().add(this);
        log.debug("add user executed");
    }


    public void removeUser(MyUser myUser){
        myUsers.remove(myUser);
        myUser.getCourses().remove(this);
        log.debug("remove user executed");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
