package com.zpy.rabbitmq.simpleDemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RabbitmqProducer {
    private static final String EXCHANGE_NAME = "simple_exchage1";
    private static final String QUEUE_NAME = "simpe_queue1";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setUsername("zhangpeiyu");
            connectionFactory.setPassword("123456");
            connectionFactory.setHost("127.0.0.1");
            connectionFactory.setPort(5672);

            connection = connectionFactory.newConnection();  //创建连接
            channel = connection.createChannel();  //创建信道

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout", true, true, null);
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "simple_route1");
            String msg = "hello world";
            channel.basicPublish(EXCHANGE_NAME, "simple_route1", true, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
            System.out.println("RabbitmqProducer published message:" + msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
