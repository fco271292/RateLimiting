package com.fco271292

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class RateLimitingApplication {

	static void main(String[] args) {
		SpringApplication.run(RateLimitingApplication, args)
	}

}
