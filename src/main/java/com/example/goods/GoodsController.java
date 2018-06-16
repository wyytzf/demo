package com.example.goods;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {
    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addGoods(@RequestBody Goods goods) {
        goodsService.saveGoods(goods);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteGoods(@PathVariable Long id) {
        goodsService.deleteGoods(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateGoods(@RequestBody Goods goods) {
        goodsService.updateGoods(goods);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Goods getGoods(@PathVariable Long id) {
        return goodsService.getGoods(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Goods> getGoods() {
        return goodsService.listGoods();
    }

}
