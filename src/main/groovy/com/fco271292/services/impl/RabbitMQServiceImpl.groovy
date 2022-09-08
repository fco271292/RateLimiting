package com.fco271292.services.impl

import java.time.LocalDateTime

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.fco271292.rabbitmq.RabbitMQConfig
import com.fco271292.rabbitmq.Receiver
import com.fco271292.services.RabbitMQService

import ch.qos.logback.classic.Logger

@Service
class RabbitMQServiceImpl implements RabbitMQService {
	
	Logger logger = LoggerFactory.getLogger(this.class)

	@Autowired
	RabbitTemplate rabbitTemplate
	
	Receiver receiver
	
	@Override
	def run() {
		
		try {
			def message = "RABBITMQ ${LocalDateTime.now()}" as String
			rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, RabbitMQConfig.routingKey, message)
		} catch (all) {
			logger.error "${all.message}"
		}
		
	}
	
}
