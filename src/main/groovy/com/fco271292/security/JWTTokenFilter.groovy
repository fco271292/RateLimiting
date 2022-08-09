package com.fco271292.security

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

import com.fco271292.repository.UserRepository

@Component
class JWTTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtTokenUtil jwtTokenUtil
	
	@Autowired
	UserRepository userRepository
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		def header = request.getHeader(HttpHeaders.AUTHORIZATION)
		if (!header || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response)
			return
		}
		
		def token = header.split(" ").getAt(1).trim()
		def userDetails = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token))
//		def roles = jwtTokenUtil.getRolesFromToken(token)
		if (!jwtTokenUtil.validate(token, userDetails)) {
			filterChain.doFilter(request, response)
			return
		}
		
		def roles = userDetails?.authorities ?: []
		def authorities = []
		if (roles) {
			authorities?.each { authority ->
				authorities	<< new SimpleGrantedAuthority(authority?.authority ?: "N/A")
			}
		}
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
			new UsernamePasswordAuthenticationToken(userDetails, null, authorities)
		usernamePasswordAuthenticationToken.details = new WebAuthenticationDetailsSource().buildDetails(request)
		
		SecurityContextHolder.context.authentication = usernamePasswordAuthenticationToken
		filterChain.doFilter(request, response)
		
	}
	
}
