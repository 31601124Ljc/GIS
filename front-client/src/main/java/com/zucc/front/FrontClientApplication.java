package com.zucc.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class FrontClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontClientApplication.class, args);
	}

}
