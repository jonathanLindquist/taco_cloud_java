package com.tacocloud.tacos.controllers.order;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ConfigurationProperties(prefix="taco.orders")
@Data
@Validated
@Scope(value="singleton")
public class OrderProps {

	@Min(value = 5, message = "must be between 5 and 25")
	@Max(value = 25, message = "must be between 5 and 25")
	private int pageSize = 20;
}
