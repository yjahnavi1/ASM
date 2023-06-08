package com.cg.aps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableSwagger2
public class Sprint1AppartmentSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sprint1AppartmentSecurityApplication.class, args);
	}

}
