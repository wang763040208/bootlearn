package com.springboot.bootlearn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EntityScan(basePackages = "com.springboot.bootlearn.*")
@EnableJpaRepositories(basePackages = "com.springboot.bootlearn.*")
@MapperScan(basePackages = "com.springboot.bootlearn.bootDao")
@EnableAsync
public class BootlearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootlearnApplication.class, args);
    }

}
