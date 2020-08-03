package com.segmentfault.springbootlesson11.controller;

import com.segmentfault.springbootlesson11.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author 62667
 */
@RestController
public class KafKaController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafKaController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @RequestMapping(value = "/message/send")
    public String sendMessage(@RequestParam String msg) {

        kafkaTemplate.send("my-replicated-topic", msg);

        return msg;
    }

    @PostMapping(value = "/user/save")
    public User saveUser(@RequestBody User user) {
        kafkaTemplate.send("users", user);
        return user;
    }
}
