package com.software.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @ClassName FanoutReceiverA
 * @Author 徐旭
 * @Date 2018/9/11 13:19
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReceiverA {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("FanoutReceiverA：" + msg);
    }
}
