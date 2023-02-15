package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.CartItemEntity;
import com.example.shopping_mall.vo.CartDetailVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    CartItemEntity findByCartIdAndItemId(Long cartId, Long ItemId);

    /*
    CartDetailVO의 생성자를 이용하여 VO를 반환할 때는
    "new com.example.shopping_mall.vo.CartDetailVO(ci.id, i.itemNm, i.price, ci.count, im.imgUrl)"
    처럼 new 키워드와 해당 VO의 패키지, 클래스명을 적어준다.
    또한 생성자의 파라미터 순서는 VO 클래스에 명시한 순으로 넣어주어야 한다.
     */
    @Query("select new com.example.shopping_mall.vo.CartDetailVO(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
            "from CartItemEntity ci, ItemImgEntity im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repImgYn = 'Y' " +
            "order by ci.regTime desc ")
    List<CartDetailVO> findCartDetailVOList(Long cartId);

}
