package io.sample.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;


@SpringBootApplication
@Import(ActiveMQConfig.class)
@ImportResource("classpath*:orderProcessing.xml")
@EnableIntegration
public class BootIntegrationJmsApp {

	public static void main(String[] args) {
        SpringApplication.run(BootIntegrationJmsApp.class, args);
    }
	

}
