package org.perscholas.studentcrm;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.studentcrm.data.AddressRepoI;
import org.perscholas.studentcrm.data.AuthGroupRepoI;
import org.perscholas.studentcrm.data.CourseRepoI;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.perscholas.studentcrm.model.*;
import org.perscholas.studentcrm.service.AddressService;
import org.perscholas.studentcrm.service.CatService;
import org.perscholas.studentcrm.service.ImageService;
import org.perscholas.studentcrm.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
    CatService catService;

    ImageService imageService;

    AuthGroupRepoI authGroupRepoI;

    @Autowired
    public MyCommandLineRunner(MyUserRepoI myUserRepoI, CourseRepoI courseRepoI, MyUserService myUserService, AddressService addressService, AddressRepoI addressRepoI, CatService catService, ImageService imageService, AuthGroupRepoI authGroupRepoI) {
        this.myUserRepoI = myUserRepoI;
        this.courseRepoI = courseRepoI;
        this.myUserService = myUserService;
        this.addressService = addressService;
        this.addressRepoI = addressRepoI;
        this.catService = catService;
        this.imageService = imageService;
        this.authGroupRepoI = authGroupRepoI;
    }

    @Value("${cat.api}")
    String cat;
    @Override
    public void run(String... args) throws Exception {


        imageService.init();

        Cat c = null;
        log.debug("CAT API: "  + cat);
//        RestTemplate restTemplate = new RestTemplate();
//
//             c = restTemplate.getForObject(cat, Cat.class);
//
//             catService.catSave(c);



        myUserRepoI.saveAndFlush(
                new MyUser("jafer", "alhaboubi","jafer@gmail.com", "password"));
        myUserRepoI.saveAndFlush(
                new MyUser("Seckin", "Dogaroglu","seckin@gmail.com", "password"));
        courseRepoI.save(new Course("Java", "seckin@gmail.com"));
        myUserRepoI.saveAndFlush(
                new MyUser("amira", "alhaboubi","amira@gmail.com", "password"));

       // myUserService.saveCourseToUser("jafer@gmail.com","Java");


        addressService.addAddressToUser(new Address("1234 tree court", "#123", "Dallas", "TX", "75555"),"jafer@gmail.com");
        addressService.addAddressToUser(new Address("456 tree court", "#555", "Plano", "TX", "75545"),"jafer@gmail.com");
        addressService.addAddressToUser(new Address("456 tree court", "#555", "Plano", "TX", "75545"),"amira@gmail.com");


        authGroupRepoI.save(new AuthGroup("jafer@gmail.com", "ROLE_ADMIN"));
        authGroupRepoI.save(new AuthGroup("jafer@gmail.com", "ROLE_USER"));
        authGroupRepoI.save(new AuthGroup("seckin@gmail.com", "ROLE_ADMIN"));
        authGroupRepoI.save(new AuthGroup("amira@gmail.com", "ROLE_USER"));



    }
}
