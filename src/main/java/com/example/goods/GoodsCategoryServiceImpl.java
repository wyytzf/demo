package com.example.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    @Autowired
    private GoodsCategoryRepository goodsCategoryRepository;

    @Override
    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryRepository.save(goodsCategory);
    }

    @Override
    @Transactional
    public void deleteGoodsCategory(Long id) {
        goodsCategoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateGoodsCategory(GoodsCategory goodsCategory) {
        goodsCategoryRepository.save(goodsCategory);
    }

    @Override
    @Transactional
    public GoodsCategory getGoodsCategory(Long id) {
        return goodsCategoryRepository.getOne(id);
    }

    @Override
    @Transactional
    public List<GoodsCategory> listGoodsCategory() {
        return goodsCategoryRepository.findAll();
    }
}
