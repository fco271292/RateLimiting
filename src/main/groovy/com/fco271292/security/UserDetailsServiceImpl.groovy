package com.fco271292.security

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

import com.fco271292.dto.UserDetailsImpl
import com.fco271292.repository.UserRepository

import ch.qos.logback.classic.Logger

class UserDetailsServiceImpl implements UserDetailsService {
	
	Logger logger = LoggerFactory.getLogger(this.class)
	
	@Autowired
	UserRepository userRepository

	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		def user
		Set<GrantedAuthority> authorities = []
		UserDetailsImpl userDetail = new UserDetailsImpl()
		logger.info "<-- ${username}"
		try {
			
			user = userRepository.findByUsername(username)
			
			if (user?.authorities) {
				user?.authorities?.each { authority ->
					authorities	<< new SimpleGrantedAuthority(authority?.authority ?: "N/A")
				}
			}
			
			userDetail.user = user
			userDetail.authorities = authorities
		} catch (UsernameNotFoundException usernameNotFoundException) {
			logger.error "${usernameNotFoundException.message}"
		} catch (all) {
			logger.error "${all.message}"
		}
		logger.info "--> ${userDetail?.toString()}"
		userDetail
	}
	
}
