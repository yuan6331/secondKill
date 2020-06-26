package com.gdut.mq;
import java.io.IOException;
import java.util.Map;
 
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.amqp.support.AmqpHeaders;
 
import com.rabbitmq.client.Channel;


@Component
public class NackTopicAckConsumer {
 
	
	/**
	 * 消费者获取消息时检查到头部包含 error 则 nack 消息
	 * @param message
	 * @param channel
	 * @param map
	 */
	@RabbitListener(queues = MqConfig.MIAOSHA_QUEUE)
	public void processMessage(String message, Channel channel,@Headers Map<String,Object> map) {
		System.out.println("-----------------------客户端  nack  收到数据 -----------------------"+message);
		System.out.println(map);
	    if (map.get("error")!= null){
	        System.out.println("错误的消息");
	        try {
	        	//此时控制台重复打印，说明该消息被 nack 后一直重新入队列然后一直重新消费
	            channel.basicNack((Long)map.get(AmqpHeaders.DELIVERY_TAG),false,true);      //否认消息
	            
	            //也可以拒绝该消息，消息会被丢弃，不会重回队列
//	            channel.basicReject((Long)map.get(AmqpHeaders.DELIVERY_TAG),false);        //拒绝消息
 
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    try {
	        channel.basicAck((Long)map.get(AmqpHeaders.DELIVERY_TAG),false);            //确认消息
	        
//	        channel.basicNack((Long)map.get(AmqpHeaders.DELIVERY_TAG),false,true);      //否认消息
	        
//	        channel.basicReject((Long)map.get(AmqpHeaders.DELIVERY_TAG),false);        //拒绝消息
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}