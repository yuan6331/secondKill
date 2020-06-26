package com.gdut.mq;

import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gdut.util.RedisUtil;

import io.lettuce.core.dynamic.annotation.Param;

@Service
@RequestMapping(value = "rabbit1")
public class RabbitSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

	@Autowired
	RabbitTemplate rabbitTemplate; // 使用RabbitTemplate,这提供了接收/发送等等方法

	@Autowired
	RedisUtil redisUtil;

	@RequestMapping(value = "/secondKill")
	@ResponseBody
	public void secondKillSender(@Param(value = "goodsId") String goodsId) {
		rabbitTemplate.setConfirmCallback(this);
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		System.out.println("callbackSender UUID: " + correlationData.getId());

		// String msg = "{\"goodsId\":\""+goodsId+"\"}";
		// 将消息携带绑定键值：secondKillRouting 发送到交换机secondKillExchange
		// rabbitTemplate.convertAndSend(MqConfig.MIAOSHA_QUEUE, goodsId);
		rabbitTemplate.convertAndSend(null, MqConfig.MIAOSHA_QUEUE, goodsId, correlationData);
	}

	/**
	 * 
	 * 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker
	 * 服务器，也就是只确认是否正确到达 Exchange 中
	 * 
	 * @param correlationData
	 * @param ack
	 * @param cause
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println("callbakck conf	irm: " + correlationData.getId());
		System.out.println("ack" + ack);
		System.out.println("cause" + cause);
		ModelAndView mv = new ModelAndView();
		if (ack) {
		}
	}

	/*
	 * 通过实现 ReturnCallback 接口，启动消息失败返回，比如路由不到队列时触发回调
	 */

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("消息主体 message : " + message);
		System.out.println("消息主体 message : " + replyCode);
		System.out.println("描述：" + replyText);
		System.out.println("消息使用的交换器 exchange : " + exchange);
		System.out.println("消息使用的路由键 routing : " + routingKey);
	}

}