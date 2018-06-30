package com.example.order;


import com.example.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Orders implements Serializable {
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
    private short stutas;

    @OneToMany
    @JoinColumn(nullable = false, name = "oid")
    private List<OrderItem> list;

    public Orders() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public BigDecimal getPost_fee() {
        return post_fee;
    }

    public String getAddress() {
        return address;
    }

    public short getStutas() {
        return stutas;
    }

    public List<OrderItem> getList() {
        return list;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public void setPost_fee(BigDecimal post_fee) {
        this.post_fee = post_fee;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStutas(short stutas) {
        this.stutas = stutas;
    }

    public void setList(List<OrderItem> list) {
        this.list = list;
    }
}

