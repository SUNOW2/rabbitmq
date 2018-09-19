package com.software.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @ClassName TopicSender
 * @Author 徐旭
 * @Date 2018/9/11 12:00
 * @Version 1.0
 */
@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send() {
        String msg1 = "I am topic.message msg ======";
        System.out.println("sender1：" + msg1);
        rabbitmqTemplate.convertAndSend("topicExchange", "topic.message", msg1);

        String msg2 = "I am topic.messages msg ******";
        System.out.println("sender2：" + msg2);
        rabbitmqTemplate.convertAndSend("topicExchange", "topic.messages", msg2);
    }
}
