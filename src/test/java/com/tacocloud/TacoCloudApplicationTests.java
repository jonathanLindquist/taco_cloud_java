package com.tacocloud;

import com.tacocloud.tacos.controllers.order.OrderProps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
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
@EnableConfigurationProperties(OrderProps.class)
//@TestPropertySource(locations = "classpath:application-test.yml")
//@EnableAutoConfiguration
//@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@AutoConfigureMockMvc
class TacoCloudApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testLoginPage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("home"))
				.andExpect(content().string(containsString("Welcome to...")));
	}

	@Test
	void orderPropsLoadsCorrectly() {
		OrderProps orderProps = new OrderProps();
		assertEquals(25, orderProps.getPageSize());
	}

}
