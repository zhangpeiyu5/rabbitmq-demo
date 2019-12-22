package com.zpy.rabbitmq.simpleDemo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MySimpleConsumer {
    private static final String QUEUE_NAME = "simpe_queue1";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("zhangpeiyu");
        connectionFactory.setPassword("123456");
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();  //创建连接
        Channel channel = connection.createChannel();  //创建信道

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);  //创建队列

        channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("MySimpleConsumer receive message:" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}
