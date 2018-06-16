package com.example.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
//    @Query("select o from order as o where o.uid = ?1 and o.deal_date between ?2 and ?3")
//    List<Orders> findByUidAndDateBetween(Long uid, Date data1, Date data2);
//
//    @Query("select o from order as o where o.gid = ?1 and o.deal_date between ?2 and ?3")
//    List<Orders> findByGidAndDateBetween(Long gid, Date data1, Date data2);

    List<Orders> findOrdersByUser(Long uid);

    List<Orders> findOrdersByGoods(Long gid);

    List<Orders> findOrdersByUserAndDealdateBetween(Long gid, Date data1, Date data2);

    List<Orders> findOrdersByGoodsAndDealdateBetween(Long gid, Date data1, Date data2);
}
