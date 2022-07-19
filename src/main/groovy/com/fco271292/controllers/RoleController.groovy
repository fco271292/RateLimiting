package com.fco271292.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.fco271292.domain.Role
import com.fco271292.services.RoleService

@RestController
@RequestMapping(path="role")
class RoleController {
	
	@Autowired
	RoleService roleService
	
	@PostMapping(path="save")
	def save(@RequestBody Role role) {
		def rsp = roleService.save(role)
		def httpStatus = rsp ? HttpStatus.OK : HttpStatus.NO_CONTENT
		new ResponseEntity(rsp, httpStatus)
	}
	
}
