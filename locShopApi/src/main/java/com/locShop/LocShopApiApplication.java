package com.locShop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("com.locShop")
@OpenAPIDefinition(
		info = @Info(title = "books API",version = "2.0.4"),
		servers = {@Server(url = "http://localhost:8088"),@Server(url = "http://oneone.com")},
		tags = {@Tag(name = "Book Store" , description = "Đây là Api của web bán sách.")}
)

@SecurityScheme(name = "BearerJWT", type = SecuritySchemeType.HTTP,scheme = "bearer", bearerFormat = "JWT",
description = "Brearer token for project books store")
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
