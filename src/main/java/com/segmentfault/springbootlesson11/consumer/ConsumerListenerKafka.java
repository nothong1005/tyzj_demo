package com.segmentfault.springbootlesson11.consumer;

import com.segmentfault.springbootlesson11.domain.Orders;
import com.segmentfault.springbootlesson11.service.OrderMybatisService;
import com.segmentfault.springbootlesson11.service.OrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author 62667
 */
@Component
public class ConsumerListenerKafka {

    private final OrderService orderService;
    private final OrderMybatisService orderMybatisService;

    public ConsumerListenerKafka(OrderService orderService, OrderMybatisService orderMybatisService) {
        this.orderService = orderService;
        this.orderMybatisService = orderMybatisService;
    }

    @KafkaListener(topics = "demo")
    public void consumer(Orders order) {

        System.out.println("消耗的数据是"+order);
        ///使用IPA
//        orderService.addOrder(order);
        orderMybatisService.addOrder(order);

    }
    @KafkaListener(topics = "my-replicated-topic")
    public void consumer(String msg) {

        System.out.println("开始消耗啦，my-replicated-topic："+msg);
        System.out.println(msg);

    }


}
