package com.fco271292.services

interface UserService {
	
	def save(def user)
	def get(def id)
	def update(def user)
	def deleted(def id)
	
}
