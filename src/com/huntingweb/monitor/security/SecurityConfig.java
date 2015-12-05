package com.huntingweb.monitor.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AccountService accountService;
	@Autowired
	private LoginNavigationHandler handler;

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountService);
	}

	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().formLogin().loginPage("/login").permitAll().usernameParameter("id")
				.passwordParameter("password").successHandler(handler).failureUrl("/login?error").and().logout()
				.permitAll().logoutSuccessUrl("/login?logout").and().authorizeRequests().regexMatchers("/")
				.hasAnyAuthority("ROLE_CLIENT").regexMatchers("/management", "/management/.*")
				.hasAuthority("ROLE_ADMIN").regexMatchers("/monitor-.*").hasAuthority("ROLE_CLIENT").and().httpBasic()
				.realmName("com.huntingweb.monitor").and().authorizeRequests()
				.regexMatchers(HttpMethod.DELETE, "/project", "/project.*", "/client", "/client.*", "/message",
						"/message.*", "/progress", "/progress.*", "/file", "/file.*")
				.authenticated()
				.regexMatchers(HttpMethod.POST, "/project", "/project.*", "/client", "/client.*", "/message",
						"/message.*", "/progress", "/progress.*", "/file", "/file.*")
				.authenticated()
				.regexMatchers(HttpMethod.PUT, "/project", "/project.*", "/client", "/client.*", "/message",
						"/message.*", "/progress", "/progress.*", "/file", "/file.*")
				.authenticated().anyRequest().permitAll();
	}
}