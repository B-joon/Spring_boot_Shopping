package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.CartItemEntity;
import com.example.shopping_mall.vo.CartDetailVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    CartItemEntity findByCartIdAndItemId(Long cartId, Long ItemId);

    @Query("select new com.example.shopping_mall.vo.CartDetailVO(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
            "from CartItemEntity ci, ItemImgEntity im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repImgYn = 'Y' " +
            "order by ci.regTime desc ")
    List<CartDetailVO> findCartDetailVOList(Long cartId);

}
