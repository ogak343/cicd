package com.example.cicdpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
public class CiCdPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CiCdPracticeApplication.class, args);
    }

}
