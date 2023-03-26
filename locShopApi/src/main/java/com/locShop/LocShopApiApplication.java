package com.locShop;

import com.locShop.Handler.JwtInterceptor;
import com.locShop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@SpringBootApplication
@ComponentScan("com.locShop")
public class LocShopApiApplication implements WebMvcConfigurer{

//	private JwtInterceptor tokenInterceptor;
//
//	public LocShopApiApplication(JwtInterceptor tokenInterceptor) {
//		this.tokenInterceptor = tokenInterceptor;
//	}
//
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(tokenInterceptor);
//	}
//
//	public void configure(WebSecurity web)  {
//		web.ignoring().requestMatchers("/v3/api-docs",
//				"/swagger-ui.html",
//				"/swagger-ui/**");
//	}

	public static void main(String[] args) {
		SpringApplication.run(LocShopApiApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*")
				.allowCredentials(false)
				.maxAge(3600);
	}
}
