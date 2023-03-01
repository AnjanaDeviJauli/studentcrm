package org.perscholas.studentcrm;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.studentcrm.data.AddressRepoI;
import org.perscholas.studentcrm.data.CourseRepoI;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.perscholas.studentcrm.model.Address;
import org.perscholas.studentcrm.model.Course;
import org.perscholas.studentcrm.model.MyUser;
import org.perscholas.studentcrm.service.AddressService;
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
    AddressService addressService;
    AddressRepoI addressRepoI;

    @Autowired
    public MyCommandLineRunner(MyUserRepoI myUserRepoI, CourseRepoI courseRepoI, MyUserService myUserService, AddressService addressService, AddressRepoI addressRepoI) {
        this.myUserRepoI = myUserRepoI;
        this.courseRepoI = courseRepoI;
        this.myUserService = myUserService;
        this.addressService = addressService;
        this.addressRepoI = addressRepoI;
    }

    @Override
    public void run(String... args) throws Exception {

        myUserRepoI.saveAndFlush(
                new MyUser("jafer", "alhaboubi","jafer@gmail.com", "password"));
        myUserRepoI.saveAndFlush(
                new MyUser("Seckin", "Dogaroglu","seckin@gmail.com", "password"));
        courseRepoI.save(new Course("Java", "seckin@gmail.com"));
        myUserRepoI.saveAndFlush(
                new MyUser("amira", "alhaboubi","amira@gmail.com", "password"));

        myUserService.saveCourseToUser("jafer@gmail.com","Java");


        addressService.addAddressToUser(new Address("1234 tree court", "#123", "Dallas", "TX", "75555"),"jafer@gmail.com");
        addressService.addAddressToUser(new Address("456 tree court", "#555", "Plano", "TX", "75545"),"jafer@gmail.com");
        addressService.addAddressToUser(new Address("456 tree court", "#555", "Plano", "TX", "75545"),"amira@gmail.com");





    }
}
