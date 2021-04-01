package com.jwt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.jwt.config.SwagerConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@Import(SwagerConfiguration.class)
public class SpringjwtauthApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringjwtauthApplication.class, args);
	}

}