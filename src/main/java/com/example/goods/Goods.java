package com.example.goods;


import javax.persistence.*;
import java.io.Serializable;


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


//    @OneToMany(mappedBy = "goods")
//    private List<Orders> orders;

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

}
