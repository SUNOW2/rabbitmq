package com.software.rabbitmq.callback;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * 描述：
 *
 * @ClassName CallBackSender
 * @Author 徐旭
 * @Date 2018/9/12 09:13
 * @Version 1.0
 */
@Component
public class CallBackSender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        rabbitTemplate.setConfirmCallback(this);
        String msg = "callbackSender：i am callback sender";
        System.out.println("CallBackSender：" + msg);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("callbackSender UUID：" + correlationData.getId());
        rabbitTemplate.convertAndSend("topicExchange", "topic.message", msg, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("callback confirm：" + correlationData.getId());
        System.out.println("ack = " + ack);
        System.out.println("cause = " + cause);
    }
}
