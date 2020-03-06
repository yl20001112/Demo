package com.cssl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 300)
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class UsersProvider7001Application {

    public static void main(String[] args) {
        SpringApplication.run(UsersProvider7001Application.class, args);
    }

}
