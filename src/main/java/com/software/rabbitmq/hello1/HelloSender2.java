package com.software.rabbitmq.hello1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 描述：
 *
 * @ClassName HelloSender2
 * @Author 徐旭
 * @Date 2018/9/11 10:58
 * @Version 1.0
 */
@Component
public class HelloSender2 {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send(String msg) {
        String sendMsg = msg + new Date();
        System.out.println("Sender2：" + sendMsg);
        rabbitmqTemplate.convertAndSend("hello", "HelloSender2：" + sendMsg);
    }
}
