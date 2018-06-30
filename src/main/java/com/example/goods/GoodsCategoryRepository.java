package com.example.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, Long> {
}
