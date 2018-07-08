package com.example.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean addOrder(Order order) {
        Order save = orderRepository.save(order);
        return save != null;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public boolean updateOrder(Order order) {
        Order save = orderRepository.save(order);
        return save != null;
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public List<Order> getListByUser(Long uid) {
        return orderRepository.findOrdersByUserId(uid);
    }

    @Override
    public List<Order> getListByStatus(int status) {
        return orderRepository.findOrdersByStatus(status);
    }

    @Override
    public Page<Order> getListByUser(Long id, Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
