package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByItemNm(String itemNm);

    List<ItemEntity> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<ItemEntity> findBtyPriceLessThan(int price);

    List<ItemEntity> findByPriceLessThanOrderByPriceDesc(int price);

    @Query(value = "select * from ITEM i where i.ITEM_DETAIL like" +
            "%:itemDetail% order by i.PRICE desc ", nativeQuery = true)
    List<ItemEntity> findByItemDetail(@Param("itemDetail") String itemDetail);

}
