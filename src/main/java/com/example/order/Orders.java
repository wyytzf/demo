package com.example.order;


import com.example.goods.Goods;
import com.example.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Orders implements Serializable {
    private static final long serialVersionUID = 4L;
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
    @Column(nullable = false, name = "dealdate")
    private Date dealdate;

    public Orders() {

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

    public void setDealdate(Date dealdate) {
        this.dealdate = dealdate;
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

    public Date getDealdate() {
        return dealdate;
    }
}
