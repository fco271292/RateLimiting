package com.fco271292.rabbitmq

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

import ch.qos.logback.classic.Logger

@Component
class Receiver {
	
	Logger logger = LoggerFactory.getLogger(this.class)
	
	void receiveMessage(def rabbitMQDTO) {
		def msn = "Received <-- ${rabbitMQDTO?.toString()}" as String
		logger.info msn
		processObject(rabbitMQDTO)
	}
	
	def processObject (def rabbitMQDTO) {
		def status = rabbitMQDTO?.status
		switch(status) {
			case true:
				logger.info "-> TRUE"
			break;
			case false:
				logger.info "-> FALSE"
			break;
			default:
				logger.info "-> DEFAULT"
			break;
		}
	}
	
}
