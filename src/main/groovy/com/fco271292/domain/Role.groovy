package com.fco271292.domain

import static javax.persistence.GenerationType.IDENTITY

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Role {
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	Long id
	
	String authority
	
	@ManyToMany(mappedBy='authorities')
	List<User> users
}
