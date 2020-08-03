package com.segmentfault.springbootlesson11.service;

import com.segmentfault.springbootlesson11.Entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author 62667
 */
@Service
public class

QueueService {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Autowired
    public QueueService(KafkaTemplate kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }


    @Async("taskExecutor")
    public CompletableFuture<Orders> sendOrderMessage(Orders order) {

        kafkaTemplate.send("demo", order);
//        System.out.println("当前的数据是："+order.toString()+"   "+"当前的线程号是："+Thread.currentThread().getId());

       return CompletableFuture.completedFuture(order);
    }
}
