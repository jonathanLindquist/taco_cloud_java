package com.tacocloud;

import com.tacocloud.tacos.controllers.order.OrderProps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//@WebMvcTest -- will not do a full auto-configure; will only instantiate beans annotated with @Controller, @ControllerAdvice & a few other WebMvc things
//@DataJpaTest
// Spring-Boot test auto-configure
//@RunWith(SpringRunner.class) //using JUnit 4
@SpringBootTest
@EnableConfigurationProperties(OrderProps.class) //needed for creating ApplicationContext with configured properties
@AutoConfigureMockMvc
class TacoCloudApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void testLoginPage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("home"))
				.andExpect(content().string(containsString("Welcome to...")));
	}

	@Test
	void orderPropsLoadsCorrectly(@Autowired OrderProps autowiredProps) {
		//application context will give configured bean
		OrderProps orderProps = applicationContext.getBean(OrderProps.class);
		assertEquals(25, orderProps.getPageSize());

		//autowired orderProps will give configured bean
		assertEquals(25, autowiredProps.getPageSize());

		//Instantiating new OrderProps will create a new bean without the configured property
		orderProps = new OrderProps();
		assertEquals(20, orderProps.getPageSize());
	}

}
