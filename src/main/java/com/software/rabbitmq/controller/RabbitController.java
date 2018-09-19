package com.software.rabbitmq.controller;

import com.software.rabbitmq.callback.CallBackSender;
import com.software.rabbitmq.fanout.FanoutSender;
import com.software.rabbitmq.hello1.HelloSender1;
import com.software.rabbitmq.hello1.HelloSender2;
import com.software.rabbitmq.hello1.HelloSender3;
import com.software.rabbitmq.hello3.UserSender;
import com.software.rabbitmq.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @ClassName RabbitController
 * @Author 徐旭
 * @Date 2018/9/11 10:04
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/rabbit")
public class RabbitController {

    @Autowired
    private HelloSender1 helloSender1;

    @Autowired
    private HelloSender2 helloSender2;

    @Autowired
    private HelloSender3 helloSender3;

    @Autowired
    private UserSender userSender;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private CallBackSender callBackSender;

    /**
     * 单生产者--单消费者
     */
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public void hello1() {
        helloSender1.send();
    }

    /**
     * 单生产者--多消费者
     */
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public void hello2() {
        for (int i = 0; i < 10; i++) {
            helloSender1.send();
        }
    }

    /**
     * 多生产者--多消费者
     */
    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    public void hello3() {
        for (int i = 0; i < 10; i++) {
            helloSender2.send("helloMessage2：" + i);
            helloSender3.send("helloMessage3：" + i);
        }
    }

    /**
     * 实体类传输
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public void user() {
        userSender.send();
    }

    /**
     * topic exchange类型rabbitmq测试，主题
     */
    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public void topic() {
        topicSender.send();
    }

    /**
     * fanout exchange类型rabbitmq测试，广播
     */
    @RequestMapping(value = "/fanout", method = RequestMethod.GET)
    public void fanout() {
        fanoutSender.send();
    }

    /**
     * 带有callback的消息发送
     */
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public void callback() {
        callBackSender.send();
    }
}
