package com.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author jimmy
 * @date 2019-06-29 08:53
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ElasticsearchStart {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchStart.class,args);
    }
}
