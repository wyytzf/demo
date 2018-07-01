package com.example.goods;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /* 商品名称 */
    private String name;
    /* 商品价格 */
    private BigDecimal price;
    /* 商品库存 */
    private int stock;
    /* 商品描述 */
    private String introduce;

    @OneToOne
    @JoinColumn(nullable = false, name = "category_id")
    /* 商品类别*/
    private GoodsCategory goodsCategory;

    public Goods() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getIntroduce() {
        return introduce;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
    }
}
