package com.software.rabbitmq.hello1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 描述：
 *
 * @ClassName HelloSender1
 * @Author 徐旭
 * @Date 2018/9/11 09:55
 * @Version 1.0
 */
@Component
public class HelloSender1 {

    /**
     * rabbitTemplate.convertAndSend("testTopicExchange", "key", "this is rabbitMq")
     * 第一个参数表示交换机
     * 第二个参数表示routing key
     * 第三个参数表示发送的消息
     */
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String sendMsg = "hello1 " + new Date();
        System.out.println("Sender1：" + sendMsg);
        rabbitTemplate.convertAndSend("hello", sendMsg);
    }
}
