package com.gdut.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/lottery")
public class LotteryController {
	
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RequestMapping(value="/turnTable")
	public String turnTable(){
		return "lottery/turnTable";
	}
	
	
	
	@RequestMapping(value = "/myReward")
	@ResponseBody
	public String queryReward() {
		rabbitTemplate.convertAndSend("lottery01", "abc");
		return "RabbitMQsending";
	}

}
