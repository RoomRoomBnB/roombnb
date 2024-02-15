package com.sparta.roombnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class RoombnbApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoombnbApplication.class, args);
        System.out.println();


    }

}
