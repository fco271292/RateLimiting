package com.fco271292.domain

import static javax.persistence.GenerationType.IDENTITY

import java.time.LocalDateTime

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Version

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.userdetails.UserDetails

@Entity
class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	Long id
	@CreationTimestamp
	LocalDateTime dateCreated
	@UpdateTimestamp
	LocalDateTime lastUpdated
	@Version
	Integer version
	
	String username
	String password
	
	boolean enabled = true
	boolean accountNonExpired
	boolean accountNonLocked
	boolean credentialsNonExpired
	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name='user_role', joinColumns=@JoinColumn(name='user_id',referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	Set<Role> authorities
	
}
