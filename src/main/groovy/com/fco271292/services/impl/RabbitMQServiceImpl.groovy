package com.fco271292.services.impl

import java.time.LocalDateTime

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.fco271292.dto.RabbitMQDTO
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
	def run(def rabbitMQDTO) {
		
		try {
//			def rabbitMQDTO = "RABBITMQ ${LocalDateTime.now()}" as String
			rabbitMQDTO = rabbitMQDTO ?: generateRabbitMQDTO()
			rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, RabbitMQConfig.routingKey, rabbitMQDTO)
		} catch (all) {
			logger.error "${all.message}"
		}
		
	}
	
	def generateRabbitMQDTO () {
		def random = new Random()
		def id = random.nextInt()
		def status = random.nextBoolean()
		def rabbitMQDTO = new RabbitMQDTO()
		rabbitMQDTO.date = LocalDateTime
		rabbitMQDTO.message = "RABBITMQ"
		rabbitMQDTO.status = status
		rabbitMQDTO.id = id
		rabbitMQDTO
	}
	
}
