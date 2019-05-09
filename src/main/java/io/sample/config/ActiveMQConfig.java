package io.sample.config;

import org.apache.activemq.ActiveMQConnectionFactory;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@Configuration
//@ConfigurationProperties(prefix = "spring.activemq")
public class ActiveMQConfig {

    public static final String IN_QUEUE = "IN_QUEUE";
    public static final String OUT_QUEUE = "IN_QUEUE";
    public static final String ERR_QUEUE = "ERR_QUEUE";
    public static final String BACK_QUEUE = "BACK_QUEUE";
    
    @Bean
    public Queue inJMSQueue() {
        return new ActiveMQQueue(IN_QUEUE);
    }
    
    @Bean
    public Queue outJMSQueue() {
        return new ActiveMQQueue(OUT_QUEUE);
    }
    
    @Bean
    public Queue backJMSQueue() {
        return new ActiveMQQueue(BACK_QUEUE);
    }
    
    @Bean
    public Queue errJMSQueue() {
        return new ActiveMQQueue(ERR_QUEUE);
    }


}