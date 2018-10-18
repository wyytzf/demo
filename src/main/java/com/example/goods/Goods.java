package com.example.goods;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
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
}
