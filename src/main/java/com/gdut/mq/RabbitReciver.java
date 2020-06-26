package com.gdut.mq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.gdut.entity.Goods;
import com.gdut.service.impl.GoodsServiceImpl;
import com.gdut.util.JsonBuilder;

@Service
public class RabbitReciver {

	@Autowired
	GoodsServiceImpl goodsService;

	@Autowired
	JsonBuilder jsonBuilder;

	@RabbitListener(queues = MqConfig.MIAOSHA_QUEUE)
	public void process(String msg) {
		Goods g = goodsService.selectOne(msg);
		if (g != null && g.getGoodsNum() > 0) {
			goodsService.updateGoodsNum(msg, g.getGoodsNum() - 1);
		} else {
			System.out.println("库存不足");
		}
	}
	
	/**
     * 手动确认消息
     * @param connectionFactory
     */
   /* @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 监听的队列
        container.setQueueNames(MqConfig.MIAOSHA_QUEUE);	
        // 手动确认
        container.setAcknowledgeMode(AcknowledgeMode.M);        
        //消息处理
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {      
            System.out.println("====接收到消息=====");
            System.out.println(new String(message.getBody()));
            if(message.getMessageProperties().getHeaders().get("error") == null){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                System.out.println("消息已经确认");
            }else {
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
                System.out.println("消息拒绝");
            }
 
        });
        return container;
    }*/

}
