package org.perscholas.studentcrm.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

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
        this.password = setPassword(password);
    }

    public MyUser(String firstName, String lastName, String email, String password, Image image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = setPassword(password);
        this.image = image;
    }

    public MyUser(int id, String firstName, String lastName, String email, String password, List<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = setPassword(password);
        this.courses = courses;
    }

    public MyUser(String firstName, String lastName, String email, String password, Set<Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = setPassword(password);
        this.addresses = addresses;
    }


    public MyUser(String firstName, String lastName, String email, String password, List<Course> courses, Image image, Set<Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = setPassword(password);
        this.courses = courses;
        this.image = image;
        this.addresses = addresses;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String firstName;

    String lastName;

    String email;

    @Setter(AccessLevel.NONE)
    String password;

    public String setPassword(String password) {
        return this.password = new BCryptPasswordEncoder().encode(password);
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "users_and_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    List<Course> courses = new ArrayList<>();



    @ToString.Exclude
    @OneToOne(mappedBy = "myUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    Image image;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "users_and_addresses",
            joinColumns = @JoinColumn(name = "my_user_id"),
            inverseJoinColumns = @JoinColumn(name = "addresses_id"))
    Set<Address> addresses = new HashSet<>();

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
