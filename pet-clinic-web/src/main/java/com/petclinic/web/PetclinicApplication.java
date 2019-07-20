package com.petclinic.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.petclinic")
public class PetclinicApplication {
//ok
	public static void main(String[] args) {
		SpringApplication.run(PetclinicApplication.class, args);
	}

}
