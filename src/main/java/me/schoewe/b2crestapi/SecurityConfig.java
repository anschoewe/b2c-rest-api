package me.schoewe.b2crestapi;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	 * Enables x509 client authentication.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests().antMatchers(HttpMethod.POST, "/**").permitAll()
		.and()
			.authorizeRequests().antMatchers(HttpMethod.GET, "/**").permitAll()
		.and()
			.cors()
		.and().
			csrf().disable();
		
		
//		http.
//			.x509()
//			.and()
//			.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.NEVER)
//			.and()
//			.csrf()
//			.disable();
	}

	/*
	 * Create an in-memory authentication manager. We create 1 user (localhost which
	 * is the CN of the client certificate) which has a role of USER.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("localhost").password("none").roles("USER");
	}
	
	

}
