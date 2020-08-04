package com.segmentfault.springbootlesson11.controller;


import com.segmentfault.springbootlesson11.domain.Orders;
import com.segmentfault.springbootlesson11.repository.OrderRepository;
import com.segmentfault.springbootlesson11.service.OrderMybatisService;
import com.segmentfault.springbootlesson11.service.QueueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * @author 62667
 */
@RestController
@Api(tags = "订单信息关系")
public class OrderController {


    private final QueueService queueService;
    private final OrderMybatisService orderMybatisService;

    @Autowired
    public OrderController(QueueService queueService, OrderMybatisService orderMybatisService) {
        this.queueService = queueService;
        this.orderMybatisService = orderMybatisService;
    }

    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    @ApiOperation("增加一个订单信息")
    @ApiImplicitParam(name = "order", value = "新增订单信息", required = true)
    public Orders addOrder(@RequestBody Orders order) {

        System.out.println("hhhh"+order.toString());
        CompletableFuture completableFuture = queueService.sendOrderMessage(order);
        completableFuture.join();


        return order;
    }

    @RequestMapping(value = "/orders/add", method = RequestMethod.POST)
    @ApiOperation("批量增加订单信息")
    @ApiImplicitParam(name = "orders", value = "新增用户信息", required = true)
    public List<Orders> addOrders(@RequestBody List<Orders> orders) {

        for (Orders order : orders) {
            System.out.println("hhhh"+order.toString());
            CompletableFuture completableFuture = queueService.sendOrderMessage(order);
            completableFuture.join();
        }

        return orders;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ApiOperation("获得所有的订单")
    public List<Orders> getOrders() {
        return orderMybatisService.getAll();
    }

}
