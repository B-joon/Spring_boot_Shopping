package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>, QuerydslPredicateExecutor<ItemEntity> {

    List<ItemEntity> findByItemNm(String itemNm);

    List<ItemEntity> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<ItemEntity> findByPriceLessThan(int price);

    List<ItemEntity> findByPriceLessThanOrderByPriceDesc(int price);

    @Query(value = "select * from ITEM i where i.ITEM_DETAIL like" +
            "%:itemDetail% order by i.PRICE desc ", nativeQuery = true)
    List<ItemEntity> findByItemDetail(@Param("itemDetail") String itemDetail);

}
