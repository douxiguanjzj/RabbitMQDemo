package cn.jzj.rabbitMQdemo;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

  /**
   * @param args
   */
  private final static String QUEUE_NAME = "hello";

  public static void main(String[] args) throws java.io.IOException, TimeoutException {
    // �������ӹ���
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    // ��������
    Connection connection = factory.newConnection();
    // ������Ϣ�ܵ�
    Channel channel = connection.createChannel();
    // ����һ����Ϊhello�Ķ���
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "Hello World!";
    // ��RabbitMQ��,��Ϣ�ǲ���ֱ�ӷ��͵�����,����Ҫ���͵�������(exchange)�С�
    // ��һ�����ձ�ʾʹ��Ĭ��exchange,�ڶ�������ʾ���͵���queue,���������Ƿ��͵���Ϣ��(�ֽ�����)
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");
    try {
		channel.close();
	} catch (TimeoutException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//�رչܵ�
    connection.close();//�ر�����
  }
}