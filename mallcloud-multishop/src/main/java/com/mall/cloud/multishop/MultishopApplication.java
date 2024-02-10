package com.mall.cloud.multishop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = { "com.mall.cloud" })
@EnableFeignClients(basePackages = {"com.mall.cloud.api.**.feign"})
public class MultishopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultishopApplication.class, args);
	}

}
