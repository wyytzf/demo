package com.example.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from order as o where o.uid = ?1 and o.deal_date between ?2 and ?3")
    Order findByUidAndDateBetween(Long uid, Date data1, Date data2);
}
