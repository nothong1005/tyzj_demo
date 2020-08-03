package com.segmentfault.springbootlesson11.job;

import com.segmentfault.springbootlesson11.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 62667
 */
@Component
public class UpdateOrderJob {

    private OrderService orderService;

    @Autowired
    public UpdateOrderJob(OrderService orderService) {
        this.orderService = orderService;
    }

    public void execute() {
        orderService.updateOrder();
    }
}
