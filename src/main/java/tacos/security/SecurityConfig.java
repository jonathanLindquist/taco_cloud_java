package tacos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		ldap authentication configuration

//		auth.ldapAuthentication()
//				.userSearchBase("ou=people")
//				.userSearchFilter("(uid={0})")
//				.groupSearchBase("ou=groups")
//				.groupSearchFilter("member={0}")
//				.passwordCompare() //compare passwords on the LDAP server directly
//				.passwordEncoder(new BCryptPasswordEncoder()) //encrypt for API Call, password on LDAP must also be encoded in similar format
//				.passwordAttribute("passcode"); //name of password field on LDAP server, defaults to checking 'userPassword'
//
//		auth.ldapAuthentication()
//				.contextSource() //use contextSource if LDAP server is not embedded, set URL to destination
//				.root("dc=tacocloud,dc=com") // use root for embedded LDAP server
//				.ldif("classpath:users.ldif"); // specify where ldap server should load user data
//				.url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");


//		----------jdbc authentication defining specific queries and password enoding-------------
//
//		auth.jdbcAuthentication()
//				.dataSource(dataSource)
//				.usersByUsernameQuery(
//						"select username, password, enabled from Users " +
//								"where username=?")
//				.authoritiesByUsernameQuery(
//						"select username, authority from UserAuthorities " +
//								"where username=?")
//				.passwordEncoder(new BCryptPasswordEncoder());

//		----------in-memory example of user configuration-----------
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
