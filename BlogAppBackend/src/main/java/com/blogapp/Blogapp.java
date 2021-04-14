package com.blogapp;
import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.blogapp.config.SwagerConfig;
import com.blogapp.services.FilesStorageService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Import(SwagerConfig.class)
public class Blogapp implements CommandLineRunner{
	
	@Resource
	FilesStorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(Blogapp.class, args);
	}
	
	@Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }
}