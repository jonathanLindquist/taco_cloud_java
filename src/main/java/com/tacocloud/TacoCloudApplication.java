package com.tacocloud;

import com.tacocloud.tacos.controllers.order.OrderProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

// annotations below are necessary if the base packages aren't in the @SpringBootApplication's (main method) package
//@SpringBootApplication(scanBasePackages = "com.tacocloud")
//@EnableJpaRepositories(basePackages = "com.tacocloud.application.tacos.data")
//@EntityScan(basePackages = "com.tacocloud.application.tacos.domain")
@SpringBootApplication
@EnableConfigurationProperties(OrderProps.class)
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}
