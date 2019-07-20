package com.example.controller;

import com.example.config.rabbitmq.RabbitMQConfig;
import com.example.entity.MqInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rabbitmq")
public class RabbitMqProductController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/testProduct")
    public String testProduct(String info) {
        MqInfo mqInfo = new MqInfo();
        mqInfo.setInfo(info);

        try {
            // 写入队列
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TEST_QUEUE_NAME, mqInfo);
            return "put ok!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
