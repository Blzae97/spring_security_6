package com.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.TimeZone;
@SpringBootApplication
public class PostAuthorizeApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        String yml = getYml();
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(PostAuthorizeApplication.class);
        springApplicationBuilder.properties(yml);

        SpringApplication springApplication = springApplicationBuilder.build();
        springApplication.run(args);
    }

    public static String getYml(){
        return "spring.config.location=" + getApplicationYml() + getDatasourceYml();
    }

    public static String getApplicationYml(){
        return "classpath:/application.yml;";
    }

    public static String getDatasourceYml(){
        return "classpath:/datasource.yml";
    }
}