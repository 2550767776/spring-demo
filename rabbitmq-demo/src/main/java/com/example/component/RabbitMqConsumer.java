package com.example.component;

import com.example.config.rabbitmq.RabbitMQConfig;
import com.example.entity.MqInfo;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * 活动订单支付消息队列监听服务
 *
 * @author ylc
 * @create 2019-05-25 10:39
 **/
@Component
public class RabbitMqConsumer {

    @RabbitListener(queues = RabbitMQConfig.TEST_QUEUE_NAME)
    public void PayNotifyMqConsumeMessage(MqInfo mqInfo, Channel channel, Message message){
        try {
            boolean b = true;
            System.out.println("----info is----"+mqInfo.getInfo());
            if(b){
                //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }else {
                //最后一个参数是：是否重回队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                //最后一个参数是：是否重回队列 false:丢弃这条消息
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
                //拒绝消息
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
                //消息被丢失
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                //消息被重新发送
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
                //多条消息被重新发送
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
