package com.Blogapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.Blogapp.config.SwagerConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
@Import(SwagerConfiguration.class)
public class Blogapp {
	public static void main(String[] args) {
		SpringApplication.run(Blogapp.class, args);
	}

}