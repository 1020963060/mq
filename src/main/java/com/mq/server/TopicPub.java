package com.mq.server;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicPub {
    public static void main(String[] args) throws JMSException {
        //1.路径 tcp协议
        String url ="tcp://192.168.40.130:61616";
        //2.创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        //3.创建连接
        Connection connection = factory.createConnection();
        //4.连接启动
        connection.start();
        //5.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //6.创建消费对象的信息
        Topic topic = session.createTopic("日志");
        //7、创建服务的提供者
        MessageProducer producer = session.createProducer(topic);
        //8、创建服务信息
        TextMessage message = session.createTextMessage("我其实也饿了");
        //9、发布服务
        producer.send(message);
        //因为这是实时发布，所有不需要关闭服务
    }
}
