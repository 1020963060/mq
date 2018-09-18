package com.mq.clien;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

import javax.jms.*;

public class Queue_Cline {
    public static void main(String[] args) throws JMSException {
        //url  tcp协议
        String url = "tcp://192.168.40.130:61616";
        //2、创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        //3、创建工厂连接对象
        Connection connection = factory.createConnection();
        //4、开启链接
        connection.start();
        //5、创建会话对象
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //6、创建接受消息的对象
        Queue queue = session.createQueue("hell");
        //7、创建消息消费者
        MessageConsumer consumer = session.createConsumer(queue);
        //8、消费者消接受读取消息
        TextMessage message = (TextMessage) consumer.receive();
        //9、打印消息内容
        System.out.println(message.getText());

    }
}
