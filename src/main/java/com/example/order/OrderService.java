package com.example.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    boolean addOrder(Orders order);

    void deleteOrder(Long id);

    boolean updateOrder(Orders order);

    Orders getOrder(Long id);

    List<Orders> getListByUser(Long uid);

    Page<Orders> getListByUser(Long id, Pageable pageable);

}
