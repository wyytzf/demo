package com.example.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrder(Orders order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void updateOrder(Orders order) {
        orderRepository.save(order);
    }

    @Override
    public Orders getOrder(Long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public List<Orders> getListByUser(Long uid) {
        return orderRepository.findOrdersByUser(uid);
    }

    @Override
    public List<Orders> getListByGoods(Long gid) {
        return orderRepository.findOrdersByGoods(gid);
    }

    @Override
    public List<Orders> getListByUserBetweenDate(Long uid, Date begin, Date end) {
        return orderRepository.findOrdersByUserAndDealdateBetween(uid, begin, end);
    }

    @Override
    public List<Orders> getListByGoodsBetweenDate(Long gid, Date begin, Date end) {
        return orderRepository.findOrdersByGoodsAndDealdateBetween(gid, begin, end);
    }

}
