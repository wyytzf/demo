package com.example.goods;

import com.example.order.Orders;
import com.example.producer.Producer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "price")
    private int price;
    @Column(nullable = false, name = "introduce")
    private String introduce;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, name = "pid")
    private Producer producer;

    @OneToMany(mappedBy = "goods")
    private List<Orders> orders;

    public Goods() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getIntroduce() {
        return introduce;
    }

    public Producer getProducer() {
        return producer;
    }
}
