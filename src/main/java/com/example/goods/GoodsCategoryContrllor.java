package com.example.goods

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class GoodsCategoryContrllor {
    @Autowired
    internal var goodsCategoryService: GoodsCategoryService? = null
}
