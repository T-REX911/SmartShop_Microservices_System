package com.trex.order_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {

    @Value("${rabbitmq.exchange}")
    private String order_exchange;

    @Value("${rabbitmq.queue}")
    private String email_queue ;

    @Value("${rabbitmq.routing-key}")
    private String email_routing_key;

    @Bean
    public TopicExchange Exchange(){
        return new TopicExchange(order_exchange);
    }

    @Bean
    public Queue queue(){
        return new Queue(email_queue);
    }

    @Bean
    public Binding emaillBinding(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(email_routing_key);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }


}
