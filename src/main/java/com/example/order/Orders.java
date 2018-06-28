package com.example.order;


import com.example.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    @Column(name = "payment")
    private String payment;
    @Column(name = "post_fee")
    private String post_fee;
    @Column(name = "address")
    private String address;
    @Column(name = "create_time")
    private Date create_time;
    @Column(name = "payment_time")
    private Date payment_time;

    @OneToMany
    @JoinColumn(nullable = false, name = "oid")
    private List<OrderItem> list;

    public Orders() {

    }


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getPayment() {
        return payment;
    }

    public String getPost_fee() {
        return post_fee;
    }

    public String getAddress() {
        return address;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public Date getPayment_time() {
        return payment_time;
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

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setPost_fee(String post_fee) {
        this.post_fee = post_fee;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    public void setList(List<OrderItem> list) {
        this.list = list;
    }
}

