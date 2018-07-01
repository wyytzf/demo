package com.example.goods;


import com.example.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize(value = "hasRole('ADMIN')")
    public Result<String> addGoods(@RequestBody Goods goods) {
        goodsService.saveGoods(goods);
        return new Result<>(200, "success", null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public Result<String> deleteGoods(@PathVariable Long id) {
        goodsService.deleteGoods(id);
        return new Result<>(200, "success", null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public Result<String> updateGoods(@RequestBody Goods goods) {
        goodsService.updateGoods(goods);
        return new Result<>(200, "success", null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    public Result<Goods> getGoods(@PathVariable Long id) {
        return new Result<>(200, "success", goodsService.getGoods(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    public Result<List<Goods>> getGoods() {
        return new Result<>(200, "success", goodsService.listGoods());
    }

}
