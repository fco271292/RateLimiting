package com.fco271292.security

import java.time.LocalDateTime

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

@Component
class JwtTokenUtil {
	
	/** tiempoMilisegundos = hora * minuto * segundo * milisegundo */
	final long EXPIRE_DURATION = 1 * 60 * 60 * 1000
	
	@Value('${springsecurity.rest.token.storage.jwt.secret}')
	String JWT_SECRET_KEY
	
	static final String TOKEN_TYPE = "Bearer"
	
	def generate(def userDetails) {
		def authorities = userDetails?.authorities ? 
			userDetails?.authorities.collect{ authority-> authority?.authority } : [:]
		def currentDate = LocalDateTime.now()
		def claims = [authorities: authorities, currentDate: currentDate as String]
		def username = userDetails?.user?.username
		def currentTime = new Date(System.currentTimeMillis())
		Jwts.builder().
			setClaims(claims).
			setSubject(username).
			setIssuedAt(currentTime).
			setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION)).
			signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY).
			compact()
	}
	
	def validate(def token, def userDetails) {
		def username = getUsernameFromToken(token)
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).body
		Boolean isTokenExpired = claims.getExpiration().before(new Date())
		username == userDetails?.username && !isTokenExpired
	}
	
	def getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).body
		claims.getSubject()
	}
	
	def getRolesFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).body
		claims['authorities']
	}
}
