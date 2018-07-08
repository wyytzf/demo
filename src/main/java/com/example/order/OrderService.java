package com.example.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    boolean addOrder(Order order);

    void deleteOrder(Long id);

    boolean updateOrder(Order order);

    Order getOrder(Long id);

    List<Order> getListByUser(Long uid);

    List<Order> getListByStatus(int status);


    Page<Order> getListByUser(Long id, Pageable pageable);

}
