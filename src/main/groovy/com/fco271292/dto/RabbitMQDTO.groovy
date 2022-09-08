package com.fco271292.dto

import groovy.transform.ToString

@ToString(includePackage = false, includeNames = true)
class RabbitMQDTO implements Serializable {
	
	def id
	def message
	def date
	def status
	
}
