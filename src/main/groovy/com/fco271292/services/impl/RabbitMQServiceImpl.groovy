package com.fco271292.services.impl

import java.time.LocalDateTime

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.fco271292.rabbitmq.RabbitMQConfig
import com.fco271292.rabbitmq.Receiver
import com.fco271292.services.RabbitMQService

@Service
class RabbitMQServiceImpl implements RabbitMQService {

	@Autowired
	RabbitTemplate rabbitTemplate
	
	Receiver receiver
	
	@Override
	def run() {
		def message = "RABBITMQ ${LocalDateTime.now()}" as String
		rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, RabbitMQConfig.routingKey, message)
	}
	
}
