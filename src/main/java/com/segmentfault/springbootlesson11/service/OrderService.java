package com.segmentfault.springbootlesson11.service;


import com.segmentfault.springbootlesson11.Entity.Orders;
import com.segmentfault.springbootlesson11.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 62667
 */
@Service
public class OrderService {

    private OrderRepository orderRepository;
    @PersistenceContext
    private EntityManager em;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void addOrder(Orders order) {
        orderRepository.save(order);
    }

    public Orders getOrderById(Long id) {

        return em.find(Orders.class, id);
    }

    public Orders getOrderByState(String state) {
        return em.find(Orders.class, state);
    }


    public void updateOrder() {

        List<Orders> orders = new ArrayList<>();
        orders = orderRepository.findByState("3");
        if ( !orders.isEmpty()) {
            for (Orders order : orders) {
                order.setState("1");
                orderRepository.save(order);
            }
            System.out.println("更新数据啦！!!");
        }
    }
}

