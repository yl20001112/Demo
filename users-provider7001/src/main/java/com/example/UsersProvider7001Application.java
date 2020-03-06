package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//服务端;
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 300)
@EnableCircuitBreaker  //支持熔断;
@EnableEurekaClient
@SpringBootApplication
public class UsersProvider7001Application {

    public static void main(String[] args) {
        SpringApplication.run(UsersProvider7001Application.class, args);
    }

}
