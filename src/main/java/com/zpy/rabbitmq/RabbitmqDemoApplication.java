package com.zpy.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@ImportResource(locations = {"classpath:beans.xml"})
@PropertySource(value = {"classpath:testzpy.yml"})
@SpringBootApplication
public class RabbitmqDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemoApplication.class, args);
    }

}
