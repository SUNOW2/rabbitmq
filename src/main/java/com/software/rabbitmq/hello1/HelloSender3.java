package com.software.rabbitmq.hello1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 描述：
 *
 * @ClassName HelloSender3
 * @Author 徐旭
 * @Date 2018/9/11 11:02
 * @Version 1.0
 */
@Component
public class HelloSender3 {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send(String msg) {
        String sendMsg = msg + new Date();
        System.out.println("HelloSender3：" + sendMsg);
        rabbitmqTemplate.convertAndSend("hello", sendMsg);
    }
}
