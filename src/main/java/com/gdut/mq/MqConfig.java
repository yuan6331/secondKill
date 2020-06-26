package com.gdut.mq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class MqConfig {

	public static final String MIAOSHA_QUEUE="secondKill.queue";
	public static final String QUEUE="queue";
	public static final String TOPIC_QUEUE="topic.queue";
	public static final String HEADER_QUEUE="header.queue";
	public static final String TOPIC_EXCHANGE="topicExchange";
	public static final String DIRECT_EXCHANGE="directExchange";
	public static final String FANOUT_EXCHANGE="fanoutExchange";
	public static final String HEADERS_EXCHANGE="headerExchange";
	
	
	
	//RabbitMQ 抽象出一个 MessageConvert 接口处理消息的序列化，其实现有 SimpleMessageConverter（默认）、Jackson2JsonMessageConverter 等
	@Bean
	public MessageConverter getMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	
	
	@Bean
	public Queue miaoshaQueue() throws IOException, TimeoutException {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-max-length", 2);
		return new Queue(MIAOSHA_QUEUE, true);
	}
	
	
	
	/**
	 * Direct模式 交换机Exchange
	 * */
	@Bean
	public Queue queue() {
		return new Queue(QUEUE, true);
	}
	@Bean
	public DirectExchange directExchange(){
		return new DirectExchange(DIRECT_EXCHANGE);
	}
	
	
	
	
	/**
	 * Topic模式 交换机Exchange
	 * */
	@Bean
	public Queue topicQueue() {
		return new Queue(TOPIC_QUEUE, true);
	}
	
	@Bean
	public TopicExchange topicExchage(){
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	
	@Bean
	public Binding topicBinding() {
		return BindingBuilder.bind(topicQueue()).to(topicExchage()).with("topic.key1");
	}
	
	
	
	
	/*
	 * Fanout模式 交换机Exchange
	 * */
	@Bean
	public FanoutExchange fanoutExchage(){
		return new FanoutExchange(FANOUT_EXCHANGE);
	}
	
	@Bean
	public Binding FanoutBinding() {
		return BindingBuilder.bind(topicQueue()).to(fanoutExchage());
	}
	
	
	
	
	/**
	 * Header模式 交换机Exchange
	 * */
	@Bean
	public HeadersExchange headersExchage(){
		return new HeadersExchange(HEADERS_EXCHANGE);
	}
	
	@Bean
	public Queue headerQueue() {
		return new Queue(HEADER_QUEUE, true);
	}
	@Bean
	public Binding headerBinding() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("header1", "value1");
		map.put("header2", "value2");
		return BindingBuilder.bind(headerQueue()).to(headersExchage()).whereAll(map).match();
	}
	
	
	
	
}
