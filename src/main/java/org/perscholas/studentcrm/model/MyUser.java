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


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class MyUser {

    public MyUser(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public MyUser(String firstName, String lastName, String email, String password, Image image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public MyUser(int id, String firstName, String lastName, String email, String password, List<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.courses = courses;
    }

    public MyUser(String firstName, String lastName, String email, String password, List<Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.addresses = addresses;
    }


    public MyUser(String firstName, String lastName, String email, String password, List<Course> courses, Image image, List<Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.courses = courses;
        this.image = image;
        this.addresses = addresses;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String firstName;

    String lastName;

    String email;

    String password;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "users_and_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    List<Course> courses = new ArrayList<>();



    @OneToOne(mappedBy = "myUser", cascade = CascadeType.ALL, orphanRemoval = true)
    Image image;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "users_and_addresses",
            joinColumns = @JoinColumn(name = "my_user_id"),
            inverseJoinColumns = @JoinColumn(name = "addresses_id"))
    List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address){
        addresses.add(address);
        address.getMyUsers().add(this);
        log.debug("add address executed");
    }


    public void removeAddress(Address address){
        addresses.remove(address);
        address.getMyUsers().remove(this);
        log.debug("remove address executed");
    }

    public void addCourse(Course c){
        courses.add(c);
        c.getMyUsers().add(this);
        log.debug("add course executed!");
    }
    public void removeCourse(Course c){
        courses.remove(c);
        c.getMyUsers().remove(this);
        log.debug("remove course executed!");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUser myUser = (MyUser) o;
        return Objects.equals(firstName, myUser.firstName) && Objects.equals(lastName, myUser.lastName) && Objects.equals(email, myUser.email) && Objects.equals(password, myUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password);
    }
}
