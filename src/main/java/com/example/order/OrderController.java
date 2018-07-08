package com.example.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/*
 * 对于订单这个业务，我们一般会考虑订单的生成，订单列表的分页查询，订单的详情，这三个接口就足够了
 * */


@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public Order getOrderById(@PathVariable long id) {
        return orderService.getOrder(id);
    }

    // todo:  "/orders", post生成订单的逻辑

    @PostMapping(value = "/orders")
    public void addOrders(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    public Page<Order> getOrders(@RequestParam(value = "id") long id,
                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "20") int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return orderService.getListByUser(id, pageable);
    }

    /*
     * 业务上一般不会对order进行更新
     * 前端传入后端的body一般不会跟你的order模型一模一样，有时候会时command，需要自己去转换
     * */
    @RequestMapping(value = "/orders", method = RequestMethod.PUT)
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<Void> updateOrders(@RequestBody Order order) {
        boolean ok = orderService.updateOrder(order);
        if (ok)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
