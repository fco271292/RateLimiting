package com.fco271292.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.fco271292.domain.User
import com.fco271292.services.UserService

@RestController
@RequestMapping(path="user")
class UserController {
	
	@Autowired
	UserService userService
	
	@PostMapping(path="save")
	def save(@RequestBody User user) {
		def rsp = userService.save(user)
		def httpStatus = rsp ? HttpStatus.OK : HttpStatus.NO_CONTENT
		new ResponseEntity(rsp, httpStatus)
	}
	
}
