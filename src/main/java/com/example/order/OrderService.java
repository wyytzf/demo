package com.example.order;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void addOrder(Order order);

    void deleteOrder(Long id);

    void updateOrder(Order order);

    Order getOrder(Long id);

    List<Order> getListByUser(Long uid);

    List<Order> getListByGoods(Long gid);
}
