package com.example.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private GoodsRepository goodsRepository;
    private GoodsCategoryRepository goodsCategoryRepository;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository, GoodsCategoryRepository goodsCategoryRepository) {
        this.goodsRepository = goodsRepository;
        this.goodsCategoryRepository = goodsCategoryRepository;
    }


    @Override
    public void saveGoods(Goods goods) {
        goodsRepository.save(goods);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsRepository.save(goods);
    }

    @Override
    public void deleteGoods(Long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public Goods getGoods(Long id) {
        return goodsRepository.getOne(id);
    }

    @Override
    public List<Goods> listGoods() {
        return goodsRepository.findAll();
    }

}
