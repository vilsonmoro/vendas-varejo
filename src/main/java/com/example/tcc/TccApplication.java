package com.example.tcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "models")
@ComponentScan(basePackages = {"controllers", "models", "services","repositories","config"})
@EnableJpaRepositories(basePackages = "repositories")
public class TccApplication {

	public static void main(String[] args) {
		String port = System.getenv("PORT");
	    if (port != null) {
	        System.setProperty("server.port", port);
	    }
		SpringApplication.run(TccApplication.class, args);
	}

}
