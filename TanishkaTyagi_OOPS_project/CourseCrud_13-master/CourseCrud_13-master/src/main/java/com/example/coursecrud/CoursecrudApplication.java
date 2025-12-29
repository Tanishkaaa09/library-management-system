package com.example.coursecrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoursecrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursecrudApplication.class, args);
        System.out.println("Course CRUD Application Started Successfully!");
        System.out.println("H2 Database Console: http://localhost:8080/h2-console");
        System.out.println("JDBC URL: jdbc:h2:mem:coursedb");
        System.out.println("Username: sa | Password: (empty)");
    }
}