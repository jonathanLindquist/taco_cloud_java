package com.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "com.tacocloud")
//@EnableJpaRepositories(basePackages = "com.tacocloud.application.tacos.data")
//@EntityScan(basePackages = "com.tacocloud.application.tacos.domain")
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}
