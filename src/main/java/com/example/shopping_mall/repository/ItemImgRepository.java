package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.ItemImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImgEntity, Long> {

    List<ItemImgEntity> findByItemIdOrderByIdAsc(Long itemId);

    ItemImgEntity findByItemIdAndRepImgYn(Long itemId, String repImgYn);

}