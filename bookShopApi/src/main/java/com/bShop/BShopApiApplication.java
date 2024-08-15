package com.bShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "books API",version = "2.0.4"),
		servers = {@Server(url = "http://192.168.1.80:8098", description = "Default "),
				@Server(url = "http://192.168.1.4:8098", description = "Nhà"),
				@Server(url = "http://192.168.1.3:8098", description = "Nhà5"),
				@Server(url = "http://192.168.1.137:8098", description = "sapa"),
				@Server(url = "http://192.168.102.210:8098", description = "thongtha"),
				@Server(url = "http://172.16.0.202:8098", description = "trường"),
				@Server(url = "http://192.168.1.62:8098", description = "Dongtay"),
				@Server(url = "http://192.168.1.83:8098", description = "tvDongtay")},
		tags = {@Tag(name = "Book Store" , description = "Đây là Api của web bán sách.")}
)

@SecurityScheme(name = "BearerJWT", type = SecuritySchemeType.HTTP,scheme = "bearer", bearerFormat = "JWT",
description = "Brearer token for project books store")
public class BShopApiApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(BShopApiApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins("http://localhost:42694")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*")
				.allowCredentials(false)
				.maxAge(3600);
	}
}
