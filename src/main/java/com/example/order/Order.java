package com.example.order;


import com.example.goods.Goods;
import com.example.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, name = "uid")
    private User user;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, name = "gid")
    private Goods goods;
    @Column(nullable = false, name = "number")
    private int number;
    @Column(nullable = false, name = "deal_date")
    private Date deal_date;

    public Order() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDeal_date(Date deal_date) {
        this.deal_date = deal_date;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Goods getGoods() {
        return goods;
    }

    public int getNumber() {
        return number;
    }

    public Date getDeal_date() {
        return deal_date;
    }
}
