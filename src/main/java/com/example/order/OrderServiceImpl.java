package com.example.order;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public List<Order> getListByUser(Long uid) {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getListByGoods(Long gid) {
        return null;
    }

}
