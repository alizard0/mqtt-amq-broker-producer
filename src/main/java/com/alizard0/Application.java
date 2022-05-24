package com.alizard0;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

public class Application {

  private static String TOPIC_NAME = "mqtt-topic";

  public static void main(final String[] args) throws Exception {
    BlockingConnection connection = connect();

    // Publish Messages
    while(true) {
      Thread.sleep(2000);
      publishMessage(connection, TOPIC_NAME, "ping");
      System.out.println("Message published!");
    }

  }

  private static void publishMessage(final BlockingConnection connection, final String topicName, final String payload) throws Exception {
    connection.publish(topicName, payload.getBytes(), QoS.AT_MOST_ONCE, false);
  }

  private static BlockingConnection connect() throws Exception {
    MQTT mqtt = new MQTT();
    mqtt.setHost("tcp://localhost:1883");
    mqtt.setCleanSession(true);
    BlockingConnection connection = mqtt.blockingConnection();
    connection.connect();
    System.out.println("Connected!");
    return connection;
  }
}
