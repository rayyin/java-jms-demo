package com.ray.jms.activemq.com.ray.jms.activemq.app;

import com.ray.jms.activemq.consumer.Consumer;

public class ConsumerMain {

    public static void main(String[] args) {
        try {
            Consumer consumer = new Consumer();
            consumer.create("test_queue.q");

            // queue consumer, point to point
            ConsumerMain testConsumer = new ConsumerMain();
            new Thread(testConsumer.new ConsumerMq(consumer)).start();
            new Thread(testConsumer.new ConsumerMq(consumer)).start();
            new Thread(testConsumer.new ConsumerMq(consumer)).start();
            new Thread(testConsumer.new ConsumerMq(consumer)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ConsumerMq implements Runnable{
        Consumer consumer;
        public ConsumerMq(Consumer consumer){
            this.consumer = consumer;
        }

        @Override
        public void run() {
            while(true){
                try {
                    consumer.getMessage(1000);
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
