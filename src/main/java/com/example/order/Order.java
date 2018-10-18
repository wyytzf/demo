package com.example.order;


import com.example.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 4L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(nullable = false, name = "uid")
    private User user;
    /* 订单花费 */
    private BigDecimal payment;
    /* 邮费 */
    private BigDecimal post_fee;
    /* 地址 */
    private String address;
    /* 订单状态 0-出库中，1-配送中，2-已签收,3-已拒收,4-申请退款中，5-申请退货中,6-完成 */
    private short status;

}

