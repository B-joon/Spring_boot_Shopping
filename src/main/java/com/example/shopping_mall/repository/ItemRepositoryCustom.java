package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.ItemEntity;
import com.example.shopping_mall.vo.ItemSearchVO;
import com.example.shopping_mall.vo.MainItemVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<ItemEntity> getAdminItemPage(ItemSearchVO itemSearchVO, Pageable pageable);

    Page<MainItemVO> getMainItemPage(ItemSearchVO itemSearchVO, Pageable pageable);

}
