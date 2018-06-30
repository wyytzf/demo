package com.example.goods;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GoodsCategoryService {
    GoodsCategory addGoodsCategory(GoodsCategory goodsCategory);

    void deleteGoodsCategory(Long id);

    void updateGoodsCategory(GoodsCategory goodsCategory);

    GoodsCategory getGoodsCategory(Long id);

    List<GoodsCategory> listGoodsCategory();
}
