package com.tacocloud.tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.tacocloud.tacos.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//		----------jdbc authentication-------------

//	@Autowired
//	DataSource dataSource;

	//		------------use custom registration details service--------------
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity //Order of antMatchers is important, precedence is ordered from first to last
				.authorizeRequests()
				.antMatchers("/design", "/orders")
					.hasRole("USER")
				.antMatchers("/", "/**", "/console/*")
					.permitAll()
				.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/design", true)
				.and()
					.logout()
					.logoutSuccessUrl("/")
				.and() // add to allow login to H2 embedded DB after adding Spring Security
					.headers()
					.frameOptions()
					.disable()
				.and() // disable csrf, not recommended (needed for access to embedded H2 console)
					.csrf()
					.disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		------------use custom registration details service--------------

		auth.userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoder());

//		------------ldap authentication configuration------------------
//
//		auth.ldapAuthentication()
//				.userSearchBase("ou=people")
//				.userSearchFilter("(uid={0})")
//				.groupSearchBase("ou=groups")
//				.groupSearchFilter("member={0}")
//				.passwordCompare() //compare passwords on the LDAP server directly
//				.bCryptPasswordEncoder(new BCryptPasswordEncoder()) //encrypt for API Call, password on LDAP must also be encoded in similar format
//				.passwordAttribute("passcode"); //name of password field on LDAP server, defaults to checking 'userPassword'
//
//		auth.ldapAuthentication()
//				.contextSource() //use contextSource if LDAP server is not embedded, set URL to destination
//				.root("dc=tacocloud,dc=com") // use root for embedded LDAP server
//				.ldif("classpath:users.ldif"); // specify where ldap server should load registration data
//				.url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");


//		----------jdbc authentication-------------
//
//		auth.jdbcAuthentication()
//				.dataSource(dataSource)
//				.usersByUsernameQuery(
//						"select username, password, enabled from Users " +
//								"where username=?")
//				.authoritiesByUsernameQuery(
//						"select username, authority from UserAuthorities " +
//								"where username=?")
//				.bCryptPasswordEncoder(new BCryptPasswordEncoder());


//		----------in-memory example of registration configuration-----------
//
//		auth.inMemoryAuthentication()
//				.withUser("buzz")
//				.password("infinity")
//				.authorities("ROLE_USER")
//				.and()
//				.withUser("woody")
//				.password("bullseye")
//				.authorities("ROLE_USER");
	}
}
