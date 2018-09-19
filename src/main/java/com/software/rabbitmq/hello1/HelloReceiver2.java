package com.software.rabbitmq.hello1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @ClassName HelloReceiver2
 * @Author 徐旭
 * @Date 2018/9/11 10:51
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver2 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver2：" + hello);
    }
}