package com.zm.zm_tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zm.*")
public class ZmToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZmToolsApplication.class, args);
    }

}
