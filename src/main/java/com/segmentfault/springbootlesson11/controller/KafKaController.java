package com.segmentfault.springbootlesson11.controller;

import com.segmentfault.springbootlesson11.domain.Orders;
import com.segmentfault.springbootlesson11.service.KafkaService;
import com.segmentfault.springbootlesson11.service.OrderMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 62667
 */
@RestController
public class KafKaController {

    private final KafkaService kafkaService;
    private final OrderMybatisService orderMybatisService;

    @Autowired
    public KafKaController(KafkaService kafkaService, OrderMybatisService orderMybatisService) {
        this.kafkaService = kafkaService;
        this.orderMybatisService = orderMybatisService;
    }

    @RequestMapping(value = "/message/send")
    public String sendMessage(@RequestParam String msg) {
        kafkaService.sendMessage("my-replicated-topic",msg);
        return msg;
    }

    @PostMapping(value = "/user/save")
    public Orders saveUser(@RequestBody Orders order) {

        kafkaService.sendMessage("users", order);
        return order;
    }
}
