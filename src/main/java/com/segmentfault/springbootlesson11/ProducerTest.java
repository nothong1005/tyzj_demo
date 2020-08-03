package com.segmentfault.springbootlesson11;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//生产者
public class ProducerTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers","localhost:9092, localhost:9093, localhost:9094");
        properties.put("key.serializer", StringSerializer.class);
        properties.put("value.serializer", StringSerializer.class);


        KafkaProducer kafkaProducer = new KafkaProducer(properties);

        ProducerRecord<String,String> producerRecord = new ProducerRecord<String,String>("my-replicated-topic", 0, "message", "This is code");

        Future<RecordMetadata> future = kafkaProducer.send(producerRecord);

        RecordMetadata recordMetadata = future.get();

        System.out.println(recordMetadata);


    }


}
