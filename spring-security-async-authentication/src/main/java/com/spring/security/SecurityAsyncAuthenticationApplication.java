package com.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.TimeZone;

@SpringBootApplication
@EnableAsync
public class SecurityAsyncAuthenticationApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        String yml = getYml();
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(SecurityAsyncAuthenticationApplication.class);
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