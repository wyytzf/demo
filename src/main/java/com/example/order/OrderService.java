package com.example.order;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrderService {
    void addOrder(Orders order);

    void deleteOrder(Long id);

    void updateOrder(Orders order);

    Orders getOrder(Long id);

    List<Orders> getListByUser(Long uid);

    List<Orders> getListByGoods(Long gid);

    List<Orders> getListByUserBetweenDate(Long uid, Date begin, Date end);

    List<Orders> getListByGoodsBetweenDate(Long gid, Date begin, Date end);
}
