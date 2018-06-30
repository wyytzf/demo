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
    public boolean addOrder(Orders order) {
        Orders save = orderRepository.save(order);
        return save != null;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public boolean updateOrder(Orders order) {
        Orders save = orderRepository.save(order);
        return save != null;
    }

    @Override
    public Orders getOrder(Long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public List<Orders> getListByUser(Long uid) {
        return orderRepository.findOrdersByUserId(uid);
    }

    @Override
    public List<Orders> getListByStatus(int status) {
        return orderRepository.findOrdersByStutas(status);
    }

    @Override
    public Page<Orders> getListByUser(Long id, Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
