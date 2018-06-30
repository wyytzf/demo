package com.example.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
//    @Query("select o from order as o where o.uid = ?1 and o.deal_date between ?2 and ?3")
//    List<Orders> findByUidAndDateBetween(Long uid, Date data1, Date data2);
//
//    @Query("select o from order as o where o.gid = ?1 and o.deal_date between ?2 and ?3")
//    List<Orders> findByGidAndDateBetween(Long gid, Date data1, Date data2);

    List<Orders> findOrdersByUserId(Long uid);


    List<Orders> findOrdersByStatus(int status);
    //
//    @Query("select o from orders as o where o.id = ?1")
//    Page<Orders> findOrdersByUserId(Long uid, Pageable pageable);
//    @Query("select o from orders as o where o.id = ?1")
//    Page<Orders> findOrdersByUser(Long uid, Pageable pageable);
}
