package com.segmentfault.springbootlesson11.job;

import com.segmentfault.springbootlesson11.service.OrderMybatisService;
import com.segmentfault.springbootlesson11.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 62667
 */
@Component
public class UpdateOrderJob {

    private OrderMybatisService orderMybatisService;

    @Autowired
    public UpdateOrderJob(OrderMybatisService orderMybatisService) {
        this.orderMybatisService = orderMybatisService;
    }

    public void execute() {
        orderMybatisService.updateOrder();
    }
}
