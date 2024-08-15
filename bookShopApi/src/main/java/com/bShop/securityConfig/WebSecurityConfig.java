package com.bShop.securityConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bShop.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private  JwtAuthFilter jwtAuthFilter;

	@Bean
	public UserDetailsService userDetailsService () {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

		@Bean
		SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
			http.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/api/auth/authenticate","/swagger-ui/**").permitAll()
			.requestMatchers("/api/web/**").hasAnyAuthority("ADMIN","USER")
			.requestMatchers("/api/admin/**").hasAuthority("ADMIN")
			.anyRequest().permitAll()
			.and()
			.authenticationProvider(authenticationProvider())
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
			return http.build();
		}

		@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
			return config.getAuthenticationManager();
		}

}
