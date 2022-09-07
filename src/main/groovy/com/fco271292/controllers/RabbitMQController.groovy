package com.fco271292.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.fco271292.services.RabbitMQService

@RequestMapping(path="rabbitmq")
@RestController
class RabbitMQController {
	
	@Autowired
	RabbitMQService rabbitMQService
	
	@GetMapping(path="/run")
	def run () {
		rabbitMQService.run()
	}
	
}
