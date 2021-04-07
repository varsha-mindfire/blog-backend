package com.blogapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.blogapp.config.SwagerConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
@Import(SwagerConfig.class)
public class Blogapp {
	public static void main(String[] args) {
		SpringApplication.run(Blogapp.class, args);
	}

}