package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.ItemEntity;
import com.example.shopping_mall.vo.ItemSearchVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<ItemEntity> getAdminItemPage(ItemSearchVO itemSearchVO, Pageable pageable);

}
