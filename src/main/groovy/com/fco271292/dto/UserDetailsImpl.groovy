package com.fco271292.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import com.fco271292.domain.User

import groovy.transform.ToString

@ToString(includeNames=true, includePackage=false, includes=['user','authorities'])
class UserDetailsImpl implements UserDetails {
	
	User user
	Set<GrantedAuthority> authorities
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		authorities
	}

	@Override
	public String getPassword() {
		user?.password
	}
	
	@Override
	public String getUsername() {
		user?.username
	}
	
	@Override
	public boolean isAccountNonExpired() {
		user?.accountNonExpired
	}
	
	@Override
	public boolean isAccountNonLocked() {
		user?.accountNonLocked
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		user?.credentialsNonExpired
	}
	
	@Override
	public boolean isEnabled() {
		user?.enabled
	}
}
