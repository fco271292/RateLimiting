package com.fco271292.rabbitmq

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

import ch.qos.logback.classic.Logger

@Component
class Receiver {
	
	Logger logger = LoggerFactory.getLogger(this.class)
	
	void receiveMessage(String message) {
		def msn = "Received <--" + message
		logger.info msn
	}
	
}
