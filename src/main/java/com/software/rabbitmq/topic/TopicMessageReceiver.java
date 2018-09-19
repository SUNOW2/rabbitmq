package com.software.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @ClassName TopicMessageReceiver
 * @Author 徐旭
 * @Date 2018/9/11 12:06
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "topic.message")
public class TopicMessageReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessageReceiver：" + msg);
    }
}
