package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>, QuerydslPredicateExecutor<ItemEntity>,
ItemRepositoryCustom{

    /*
    itemNm(상품명)으로 데이터를 조회하기 위해서 By 뒤에 필드명인 itemNm을 메소드의 이름을 붙인다.
    엔티티명은 생량이 가능하므로 findByItemNm으로 메소드 명을 만들어주고 매개 변수로는 검색할 때 사용할 상품명
    변수를 넘겨준다.
     */
    List<ItemEntity> findByItemNm(String itemNm);

    /*
    상품을 상품명과 상품 상세 설명을 OR 조건을 이용하여 조회하는 쿼리 메소드이다.
     */
    List<ItemEntity> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    /*
    파라미터로 넘어온 price 변수보다 값이 작은 상품 데이터를 조회하는 쿼리 메소드이다.
     */
    List<ItemEntity> findByPriceLessThan(int price);

    List<ItemEntity> findByPriceLessThanOrderByPriceDesc(int price);

    /*
    @Query 어노테이션 안에 JPQL로 작성한 쿼리문을 넣는다. from 뒤에는 엔티티 클래스로 작성한 Item을
    지정해주고 Item으로부터 데이터를 select하겠다는 것을 의미함.
    파라미터에 @Param 어노테이션을 이용하여 파라미터로 넘어온 값을 JPQL에 들어갈 변수로 지정해줄 수 있다
    아래 코드는 itemDetail 변수를 "like % %"사이에 ":itemDetail"로 값이 들어가도록 작성함
     */
    @Query(value = "select * from ITEM i where i.ITEM_DETAIL like" +
            "%:itemDetail% order by i.PRICE desc ", nativeQuery = true)
    List<ItemEntity> findByItemDetail(@Param("itemDetail") String itemDetail);

}
