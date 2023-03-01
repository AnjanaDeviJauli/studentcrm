package org.perscholas.studentcrm;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// @EnableEncryptableProperties
public class StudentCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCrmApplication.class, args);
	}



}
