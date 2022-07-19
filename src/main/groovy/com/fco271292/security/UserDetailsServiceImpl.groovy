package com.fco271292.security

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

import com.fco271292.domain.User
import com.fco271292.repository.UserRepository

import ch.qos.logback.classic.Logger

class UserDetailsServiceImpl implements UserDetailsService {
	
	Logger logger = LoggerFactory.getLogger(this.class)
	
	@Autowired
	UserRepository userRepository

	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		def user = userRepository.findByUsername(username)
	}
	
}
