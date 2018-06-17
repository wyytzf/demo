package com.example.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
    public List<Orders> getOrderListByUser(@PathVariable Long uid) {
        return orderService.getListByUser(uid);
    }
    //
    //
    @RequestMapping(value = "/goods/{gid}", method = RequestMethod.GET)
    public List<Orders> getOrderListByProducer(@PathVariable Long gid) {
        return orderService.getListByGoods(gid);
    }

}
