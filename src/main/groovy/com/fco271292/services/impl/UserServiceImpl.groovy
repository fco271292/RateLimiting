package com.fco271292.services.impl

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

import com.fco271292.repository.UserRepository
import com.fco271292.services.UserService

import ch.qos.logback.classic.Logger

@Service
class UserServiceImpl implements UserService {
	
	Logger logger = LoggerFactory.getLogger(this.class)
	
	@Autowired
	UserRepository userRepository
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder()

	@Override
	def save(Object user) {
		def rsp
		try {
			def password = user?.password
			def enabled = true
			def accountNonExpired = true
			def accountNonLocked = true
			def credentialsNonExpired = true
			user?.password = bCryptPasswordEncoder.encode(password)
			user?.enabled = enabled
			user?.accountNonExpired = accountNonExpired
			user?.accountNonLocked = accountNonLocked
			user?.credentialsNonExpired = credentialsNonExpired
			rsp = userRepository.save(user)
		} catch (all) {
			logger.error "${all.message}"
		}
		rsp
	}

	@Override
	def get(def id) {
		def rsp
		try {
			rsp = userRepository.findById(id)
		} catch (all) {
			logger.error "${all.message}"
		}
		rsp
	}

	@Override
	def update(Object user) {
		try {
			
		} catch (all) {
			logger.error "${all.message}"
		}
	}

	@Override
	def deleted(Object id) {
		try {
			
		} catch (all) {
			logger.error "${all.message}"
		}
	}
}
