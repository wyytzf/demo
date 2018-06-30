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
    /* 数量 */
    private int num;
    /* 成交价 */
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

    public int getNum() {
        return num;
    }

    public double getPrice() {
        return price;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
