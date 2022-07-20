package com.fco271292.services.impl

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

import com.fco271292.repository.RoleRepository
import com.fco271292.services.RoleService

import ch.qos.logback.classic.Logger

@Service
class RoleServiceImpl implements RoleService {
	
	Logger logger = LoggerFactory.getLogger(this.class)
	
	@Autowired
	RoleRepository roleRepository
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder()

	@Override
	def save(def role) {
		def rsp
		try {
			rsp = roleRepository.save(role)
		} catch (all) {
			logger.error "${all.message}"
		}
		rsp
	}

	@Override
	def get(def id) {
		try {
			
		} catch (all) {
			logger.error "${all.message}"
		}
	}

	@Override
	def update(def role) {
		try {
			
		} catch (all) {
			logger.error "${all.message}"
		}
	}

	@Override
	def deleted(def id) {
		try {
			
		} catch (all) {
			logger.error "${all.message}"
		}
	}

	
}
