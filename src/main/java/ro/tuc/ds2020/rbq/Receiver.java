package ro.tuc.ds2020.rbq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ro.tuc.ds2020.dtos.MeasurementsDto;
import ro.tuc.ds2020.services.MeasurementsService;

import javax.security.auth.login.Configuration;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver extends Thread{
    private MeasurementsService measurementsService;

    private static String queue_name = "SdAssignment2";
    private static String host_name = "goose.rmq2.cloudamqp.com";
    private static String username = "eesuskpb";
    private static String virtual_host_name = "eesuskpb";
    private static String password = "4BySMiy_hHc7P9gZMxB14XdOBfZqzbel";

    public Receiver(MeasurementsService meas){
        this.measurementsService=meas;
    }

    private static ConnectionFactory connection(){
        ConnectionFactory conn = new ConnectionFactory();
        conn.setHost(host_name);
        conn.setUsername(username);
        conn.setVirtualHost(virtual_host_name);
        conn.setPassword(password);
        conn.setRequestedHeartbeat(30);
        conn.setConnectionTimeout(30000);
        return conn;
    }

    public static void main(int threadNr) throws Exception {
        ConnectionFactory factory = connection();
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(queue_name, true, false, false, null);
        System.out.println(" X Waiting for messages.");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" X Thread " + threadNr + " - message : "+message);
            try {
                doWork(message);
            } finally {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(queue_name, false, deliverCallback, consumerTag -> { });
    }

    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}


