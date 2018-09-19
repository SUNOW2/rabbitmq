package com.software.rabbitmq.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @ClassName FanoutSender
 * @Author 徐旭
 * @Date 2018/9/11 13:15
 * @Version 1.0
 */
@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send() {
        String msgString = "fanoutSender：hello，I am hzb";
        System.out.println(msgString);
        rabbitmqTemplate.convertAndSend("fanoutExchange", "abc.def", msgString);
    }
}
