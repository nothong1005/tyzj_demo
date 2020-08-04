package com.segmentfault.springbootlesson11.service;

import com.segmentfault.springbootlesson11.domain.Orders;
import com.segmentfault.springbootlesson11.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 62667
 */
@Service
public class OrderMybatisService {

    private final OrderMapper orderMapper;

    public OrderMybatisService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public List<Orders> getAll() {
        return orderMapper.selectAll();
    }

    public List<Orders> getOrderByState(String state) {
        return orderMapper.selectByState(state);
    }

    public void addOrder(Orders order) {
        orderMapper.insert(order);
        System.out.println("成功保存数据");
    }

    public void addOrders(List<Orders> orders) {
        for (Orders order : orders) {
            orderMapper.insert(order);
        }
    }

    public void updateOrder() {
        List<Orders> orders;
        orders = orderMapper.selectByState("3");
        if ( !orders.isEmpty()) {
            for (Orders order : orders) {
                order.setState("1");
                orderMapper.insert(order);
            }
            System.out.println("更新数据啦！!!");
        }
    }
}
