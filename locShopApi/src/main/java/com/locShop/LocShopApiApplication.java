package com.locShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("com.locShop")
public class LocShopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocShopApiApplication.class, args);
	}

}
