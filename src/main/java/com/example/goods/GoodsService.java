package com.example.goods;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsService {
    void saveGoods(Goods goods);

    void updateGoods(Goods goods);

    void deleteGoods(Long id);

    Goods getGoods(Long id);

    List<Goods> listGoods();
}
