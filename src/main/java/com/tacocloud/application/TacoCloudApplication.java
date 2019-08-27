package com.tacocloud.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.tacocloud")
@EnableJpaRepositories(basePackages = "com.tacocloud.tacos.data")
@EntityScan(basePackages = "com.tacocloud.tacos.domain")
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}
