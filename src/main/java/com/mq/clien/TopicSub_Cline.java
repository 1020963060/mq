package com.mq.clien;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicSub_Cline {
    public static void main(String[] args) throws JMSException {
        //地址  tcp协议
        String url = "tcp://192.168.40.130:61616";
        //创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        //3、创建工厂连接对象
        Connection connection = factory.createConnection();
        //4、启动连接
        connection.start();
        //5、创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //6、创建接受消息的对象
        Topic topic = session.createTopic("日志");
        //7、创建消费对象
        MessageConsumer consumer = session.createConsumer(topic);
        //8、消费者接受消息
        TextMessage message = (TextMessage) consumer.receive();
        //9、消费者接受信息的监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                try {
                    //输出监听到的内容
                    System.out.println("接受："+ ((TextMessage)message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("111111：" + message.getText());
    }
}
