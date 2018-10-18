package com.example.order;

import com.example.goods.Goods;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
//    @Column(nullable = false, name = "oid", insertable = false, updatable = false)
    private long oid;
    /* 数量 */
    private int num;
    /* 成交价 */
    private double price;
    @OneToOne
    @JoinColumn(nullable = false, name = "gid")
    private Goods goods;
}
