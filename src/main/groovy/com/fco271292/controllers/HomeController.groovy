package com.fco271292.controllers

import java.time.LocalDateTime

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path="home")
class HomeController {
	
	@GetMapping(path="/index")
	def index () {
		def rsp = [home: LocalDateTime.now()]
		println "--> ${rsp}"
		new ResponseEntity<>(rsp, HttpStatus.OK)
	}
}
