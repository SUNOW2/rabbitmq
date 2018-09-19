package com.software.rabbitmq.hello1;

import com.software.rabbitmq.po.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @ClassName HelloReceiver1
 * @Author 徐旭
 * @Date 2018/9/11 10:02
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver1 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1：" + hello);
    }
}

/**
 * @RabbitListener：当队列中有消息的时候，该注解修饰下的方法会被执行
 * @RabbitHandler：接收者可以监听多个队列，不同的队列消息的类型可能不同，该注解可以使得不同的消息让不同的方法来相应
 */