package com.fco271292.rabbitmq

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
	
	static final String topicExchangeName = "RateLimit-Exchange"
	static final String queueName = "RateLimit"
	static final String routingKey = "com.fco271292"
	
	@Bean
	Queue queue () {
		new Queue(queueName, false)
	}
	
	@Bean
	TopicExchange exchange () {
		new TopicExchange(topicExchangeName)
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		BindingBuilder.bind(queue).to(exchange).with(routingKey)
	}
	
	@Bean
	SimpleMessageListenerContainer container (ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer()
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory)
		simpleMessageListenerContainer.setQueueNames(queueName)
		simpleMessageListenerContainer.setMessageListener(messageListenerAdapter)
		simpleMessageListenerContainer
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter (Receiver receiver) {
		new MessageListenerAdapter(receiver, "receiveMessage")
	}
	
}
