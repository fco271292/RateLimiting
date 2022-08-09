package com.fco271292.security

class AuthResponse {
	
	String username
	List<String> roles = []
	Long expires_in
	String token_type
	String refresh_token
	String access_token
	
}
