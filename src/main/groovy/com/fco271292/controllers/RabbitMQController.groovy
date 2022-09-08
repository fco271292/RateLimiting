package com.fco271292.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.fco271292.dto.RabbitMQDTO
import com.fco271292.services.RabbitMQService

@RequestMapping(path="rabbitmq")
@RestController
class RabbitMQController {
	
	@Autowired
	RabbitMQService rabbitMQService
	
	@PostMapping(path="/run")
	def run (@RequestBody RabbitMQDTO rabbitMQDTO) {
		rabbitMQService.run(rabbitMQDTO)
		def httpStatus = HttpStatus.ACCEPTED
		new ResponseEntity(httpStatus)
	}
	
}
