package com.tacocloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//@WebMvcTest -- will not do a full auto-configure; will only instantiate beans annotated with @Controller, @ControllerAdvice & a few other WebMvc things
//@DataJpaTest
// Spring-Boot test auto-configure
//@RunWith(SpringRunner.class) //using JUnit 4
@SpringBootTest
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
	void contextLoads() {
	}

}
