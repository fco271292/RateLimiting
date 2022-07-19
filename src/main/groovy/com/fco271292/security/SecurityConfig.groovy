package com.fco271292.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@SuppressWarnings("deprecation")
class SecurityConfig extends WebSecurityConfigurerAdapter {

	UserDetailsServiceImpl userDetailsServiceImpl

	PasswordEncoder passwordEncoder

	@Bean
	def passwordEncoder() {
		passwordEncoder = new BCryptPasswordEncoder()
	}
	
	@Bean
	def userDetailServiceImpl() {
		userDetailsServiceImpl = new UserDetailsServiceImpl()
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailServiceImpl()).passwordEncoder(passwordEncoder())
	}

	@Override
	protected void configure( HttpSecurity http) throws Exception {
		http
		.httpBasic().and()
//		.addFilterAfter(new JWTTokenFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		  .antMatchers("/user/**").permitAll()
		  .antMatchers("/role/**").permitAll()
		  .anyRequest().authenticated().and()
		.csrf().disable()
	}

}
