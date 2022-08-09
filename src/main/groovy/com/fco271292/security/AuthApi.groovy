package com.fco271292.security

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import com.fco271292.dto.UserDetailsImpl

import ch.qos.logback.classic.Logger

@RestController
//@RequestMapping(path="api/public")
class AuthApi {
	
	Logger logger = LoggerFactory.getLogger(this.class)
	
	AuthenticationManager authenticationManager
	
	public AuthApi(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Autowired
	JwtTokenUtil jwtTokenUtil
	
	@PostMapping(path="/api/login")
	def login (@RequestBody AuthRequest authRequest) {
		
		try {
			def usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
			def user = authentication.principal as UserDetailsImpl
			def authorities = user?.authorities ? user?.authorities?.collect { authority-> authority?.authority} : []
			def expiresIn = jwtTokenUtil.EXPIRE_DURATION / 1000
			def tokenType = jwtTokenUtil.TOKEN_TYPE
			def refreshToken = ""
			def accessToken = jwtTokenUtil.generate(user)
			def authResponse = new AuthResponse()
			authResponse.with { 
				username = user.username
				roles = authorities
				expires_in = expiresIn
				token_type = tokenType
				refresh_token = refreshToken
				access_token = accessToken
			}
			def httpStatus = HttpStatus.OK
			new ResponseEntity(authResponse, httpStatus)
		} catch (all) {
			logger.error "${all.message}"
			def httpStatus = HttpStatus.UNAUTHORIZED
			new ResponseEntity(httpStatus)
		}
		
	}
	
}
