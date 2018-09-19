package com.software.rabbitmq.hello3;

import com.software.rabbitmq.po.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @ClassName UserSender
 * @Author 徐旭
 * @Date 2018/9/11 11:20
 * @Version 1.0
 */
@Component
public class UserSender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send() {
        User user = new User();
        user.setName("徐旭");
        user.setPass("12345");

        System.out.println("userSender：" + user);

        rabbitmqTemplate.convertAndSend("user", user);
    }
}
