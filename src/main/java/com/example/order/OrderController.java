package com.example.order;

import com.example.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public Result<Orders> getOrderById(@PathVariable long id) {
        return new Result<>(200, "success", orderService.getOrder(id));
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public Result<List<Orders>> getOrderByUserId(@PathVariable long id) {
        return new Result<>(200, "success", orderService.getListByUser(id));
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public Result<List<Orders>> getOrderByStatus(@RequestParam int status) {
        return new Result<>(200, "success", orderService.getListByStatus(status));
    }

    // todo:  "/orders", post生成订单的逻辑
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @PreAuthorize(value = "hasRole('ADMIN')")
    @Transactional
    public Result<String> updateOrder(Orders orders) {
        Long id = orders.getId();
        Result<Orders> orderById = getOrderById(id);
        return null;
    }


}
