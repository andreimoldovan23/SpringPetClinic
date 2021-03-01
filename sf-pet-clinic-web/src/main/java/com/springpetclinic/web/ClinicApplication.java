package com.springpetclinic.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.springpetclinic.data.repositories"})
@EntityScan(basePackages = {"com.springpetclinic.data.model"})
@ComponentScan(basePackages = {"com.springpetclinic.data", "com.springpetclinic.web"})
public class ClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicApplication.class, args);
    }

}
