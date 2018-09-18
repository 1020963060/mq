package com.mq.server;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueMq {
    public static void main(String[] args) throws JMSException {
        //url  tcp协议
        String url = "tcp://192.168.40.130:61616";
        //创建工厂对象
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //3.创建连接对象
        Connection connection = connectionFactory.createConnection();
        //4.开启链接
        connection.start();
        //5.获取会话，1，是否开始事务，此处是false，
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
       //6.创建存储消息的对象
        Queue queue = session.createQueue("hell");
        //7.创建消息提供者
        MessageProducer producer = session.createProducer(queue);
        //8.创建消息
        TextMessage message = session.createTextMessage("hello,我是大家好");
        //9.发送消息
        producer.send(message);
        //10.关闭服务
        producer.close();
        //session.commit();
        session.close();
        connection.close();

    }
}
