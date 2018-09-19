package com.software.rabbitmq.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 描述：
 *
 * @ClassName RabbitMqConfig
 * @Author 徐旭
 * @Date 2018/9/11 13:12
 * @Version 1.0
 */
@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String addresses;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    @Bean
    public Queue helloQueue() {
        return new Queue("hello", true);
    }

    @Bean
    public Queue userQueue() {
        return new Queue("user", true);
    }

    /**
     * 验证topic Exchange的队列：topic转发模式
     *
     * @return
     */
    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message", true);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue("topic.messages", true);
    }

    /**
     * 验证fanout Exchange的队列：fanout广播模式，发送到该路由器的消息会使得绑定到该路由
     * 器的每一个Queue接收到消息，这时候即使指定了key，或者规则（convertAndSend方法的
     * 参数2），也会被忽略
     *
     * @return
     */
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A", true);
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B", true);
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C", true);
    }

    @Bean
    public Queue DMessage() {
        return new Queue("fanout.D", true);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 将队列queueMessage与exchange绑定，binging_key为topic.message，就是完全匹配
     *
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 将队列queueMessages与exchange绑定，binding_key为topic.#，模糊匹配
     *
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

    /**
     * 将队列AMessage与exchange绑定
     *
     * @param AMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange exchange) {
        return BindingBuilder.bind(AMessage).to(exchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange exchange) {
        return BindingBuilder.bind(BMessage).to(exchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange exchange) {
        return BindingBuilder.bind(CMessage).to(exchange);
    }

    @Bean
    Binding bindingExchangeD(Queue DMessage, FanoutExchange exchange) {
        return BindingBuilder.bind(DMessage).to(exchange);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();

        connectionFactory.setAddresses(addresses + ":" + port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        // 如果要进行消息回调，则这里必须要设置为true
        connectionFactory.setPublisherConfirms(publisherConfirms);

//        Connection connection = connectionFactory.createConnection();
//        Channel channel = connection.createChannel(true);

        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
}
