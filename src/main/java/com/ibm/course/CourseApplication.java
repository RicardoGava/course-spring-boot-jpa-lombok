package com.ibm.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApplication {

    public static void main(String[] args) {
        System.out.println("INÍCIO!");
        SpringApplication.run(CourseApplication.class, args);
        System.out.println("FIM!");
    }

}
