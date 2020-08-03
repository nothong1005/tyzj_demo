package com.segmentfault.springbootlesson11.consumer;

import com.segmentfault.springbootlesson11.Entity.User;
import com.segmentfault.springbootlesson11.Entity.Orders;
import com.segmentfault.springbootlesson11.service.OrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListenerKafka {

    private OrderService orderService;

    public ConsumerListenerKafka(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "demo")
    public void consumer(Orders order) {

        System.out.println("消耗的数据是"+order);
        orderService.addOrder(order);

    }
    @KafkaListener(topics = "my-replicated-topic")
    public void consumer(String msg) {

        System.out.println("开始消耗啦，my-replicated-topic："+msg);
        System.out.println(msg);

    }

    @KafkaListener(topics = "users")
    public void consumer(User user) {
        System.out.println("这是user主题的"+user.toString());
        System.err.println(user);

    }
}
