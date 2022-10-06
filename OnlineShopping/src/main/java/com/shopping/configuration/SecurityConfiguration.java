package com.shopping.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shopping.service.impl.UserServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserServiceImpl userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll().and().exceptionHandling().accessDeniedPage("/logout");

		http.formLogin().loginPage("/login").loginProcessingUrl("/login").usernameParameter("account")
				.passwordParameter("password").defaultSuccessUrl("/home")
				.failureUrl("/login?err=Something went wrong, please try again.").permitAll();
		http.logout().logoutSuccessUrl("/login").permitAll();
	}

}
