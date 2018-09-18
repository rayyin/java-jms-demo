package com.ray.jms.activemq.producer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;

    public void create(String destinationName) throws JMSException {

        LOGGER.info("ActiveMQConnection.DEFAULT_BROKER_URL {}", ActiveMQConnection.DEFAULT_BROKER_URL);

        // create a Connection Factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

        // create a Connection
        connection = connectionFactory.createConnection();

        // create a Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // create the Destination to which messages will be sent
        Destination destination = session.createQueue(destinationName);

        // create a Message Producer for sending messages
        messageProducer = session.createProducer(destination);
    }

    public void close() throws JMSException {
        connection.close();
    }

    public void sendMessage(String message) throws JMSException {

        String text = message;

        // create a JMS TextMessage
        TextMessage textMessage = session.createTextMessage(text);

        // send the message to the queue destination
        messageProducer.send(textMessage);

        LOGGER.debug("producer sent message with text='{}'", text);
    }
}
