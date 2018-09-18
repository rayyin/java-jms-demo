package com.ray.jms.activemq.com.ray.jms.activemq.app;

import com.ray.jms.activemq.producer.Producer;

public class ProducerMain {

    public static void main(String[] args) {

        try {
            Producer producer = new Producer();
            producer.create("test_queue.q");
            ProducerMain testMq = new ProducerMain();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Thread 1
            new Thread(testMq.new ProductorMq(producer)).start();
            //Thread 2
            new Thread(testMq.new ProductorMq(producer)).start();
            //Thread 3
            new Thread(testMq.new ProductorMq(producer)).start();
            //Thread 4
            new Thread(testMq.new ProductorMq(producer)).start();
            //Thread 5
            new Thread(testMq.new ProductorMq(producer)).start();
        } catch(Exception e) {

        }
    }

    private class ProductorMq implements Runnable{
        Producer producer;
        public ProductorMq(Producer producer){
            this.producer = producer;
        }

        @Override
        public void run() {
            while(true){
                try {
                    producer.sendMessage("Jaycekon-MQ");
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


