package com.example.putian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.putian.mapper")
@SpringBootApplication
public class PutianApplication {

    public static void main(String[] args) {
        SpringApplication.run(PutianApplication.class, args);
    }

}
