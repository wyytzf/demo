package com.example.order;

import com.example.goods.Goods;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @Column(nullable = false, name = "oid", insertable = false, updatable = false)
    private long oid;
    @Column(nullable = false, name = "num")
    private int num;
    @Column(nullable = false, name = "price")
    private double price;
    @OneToOne
    @JoinColumn(nullable = false, name = "gid")
    private Goods goods;

    public OrderItem() {

    }

    public Long getId() {
        return id;
    }

    public long getOid() {
        return oid;
    }

    public Goods getGoods() {
        return goods;
    }

    public int getNum() {
        return num;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
