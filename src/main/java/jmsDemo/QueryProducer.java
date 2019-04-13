package jmsDemo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueryProducer {
	public static void main(String[] args) throws JMSException {
		//1. 创建连接工厂
		//ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.159.132:61616");
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.159.132:61616");
		//2.创建连接
		Connection connection = connectionFactory.createConnection();
		//3.启动连接
		connection.start();
		//4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5.创建队列对象
		Queue queue = session.createQueue("test-queue");
		//6.创建消息生产者对象
		MessageProducer producer = session.createProducer(queue);
		//7.创建消息对象（文本消息）
		TextMessage textMessage = session.createTextMessage("Welcome  To  Here");
		//8.发送消息
		producer.send(textMessage);
		//9.关闭资源
		producer.close();
		session.close();
		connection.close();
		
	}
}