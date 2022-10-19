package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ProductDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDetailsApplication.class, args);
	}

}
