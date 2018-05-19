package cn.jzj.rabbitMQdemo;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Receiver {

  private final static String QUEUE_NAME = "hello";

  public static void main(String avg[]) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {
    // �������ӹ���
    ConnectionFactory factory = new ConnectionFactory();

    //Ĭ�����ӵ�������
    factory.setHost("localhost");
    // ��������
    Connection connection = factory.newConnection();
    // ������Ϣ�ܵ�
    Channel channel = connection.createChannel();
    // ����һ����Ϊhello�Ķ���,��ֹ���в�����
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);//1.������2.�Ƿ�־û�,3�Ƿ����������,4����ʹ���Ƿ�ɾ��,5����������
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    // ����һ��������
    QueueingConsumer consumer = new QueueingConsumer(channel);
    channel.basicConsume(QUEUE_NAME, true, consumer);

    while (true) {
      // ѭ����ȡ��Ϣ
      QueueingConsumer.Delivery delivery = consumer.nextDelivery();//ָ����һ����Ϣ,���û�л�һֱ����
      String message = new String(delivery.getBody());
      System.out.println(" [x] Received '" + message + "'");
    }
  }
}