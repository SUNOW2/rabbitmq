package com.software.rabbitmq.hello3;

import com.software.rabbitmq.po.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @ClassName UserReceiver
 * @Author 徐旭
 * @Date 2018/9/11 11:23
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "user")
public class UserReceiver {

    @RabbitHandler
    public void process(User user) {
        System.out.println("UserReceiver：" + user);
    }
}
