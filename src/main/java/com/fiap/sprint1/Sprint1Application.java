package com.fiap.sprint1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Sprint1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sprint1Application.class, args);
	}

}
