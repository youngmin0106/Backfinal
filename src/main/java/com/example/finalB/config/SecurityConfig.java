package com.example.finalB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable();
		

		http.authorizeRequests(
				authorize -> authorize
				.antMatchers(HttpMethod.POST, "/idoverlap", "/signup", "/login").permitAll()
				.antMatchers(HttpMethod.GET, "/oauth/kakao").permitAll()
				.anyRequest().authenticated() // 나머지 요청에 대해 인증을 요구
		);

		return http.build();
	}

	@Bean
	public CorsConfigurationSource configurationSource() {

		CorsConfiguration config = new CorsConfiguration(); // cors 설정 객체

		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return source;
	}

	// 비밀번호 암호화
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 인증객체
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
