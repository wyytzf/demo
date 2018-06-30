package com.example.goods;

import com.example.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goodcategory")
public class GoodsCategoryContrllor {
    @Autowired
    GoodsCategoryService goodsCategoryService;


    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public Result<String> addGoodsCategory(@RequestBody GoodsCategory goodsCategory) {
        goodsCategoryService.addGoodsCategory(goodsCategory);
        return new Result<>(200, "success", null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public Result<String> deleteGoodsCategory(@PathVariable Long id) {
        goodsCategoryService.deleteGoodsCategory(id);
        return new Result<>(200, "success", null);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public Result<String> updateGoodsCategory(@RequestBody GoodsCategory goodsCategory) {
        goodsCategoryService.updateGoodsCategory(goodsCategory);
        return new Result<>(200, "success", null);
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Result<List<GoodsCategory>> listGoodsCategory() {
        List<GoodsCategory> goodsCategories = goodsCategoryService.listGoodsCategory();
        return new Result<>(200, "success", goodsCategories);
    }


}
