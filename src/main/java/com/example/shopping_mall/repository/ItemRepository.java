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

    /*
    출력 결과를 OrderBy 키워드를 이용한다면 오름차순 또는 내림차순으로 조회할 수 있다.
    오름차순인 경우 'OrderBy + 속성명 + Asc 키워드'를 이용하고,
    내림차순에서는 OrderBy + 속성명 + Desc 키워드'를 이용해 데이터 순서를 처리할 수 있다.
     */
    List<ItemEntity> findByPriceLessThanOrderByPriceDesc(int price);

    /*
    @Query 어노테이션 안에 JPQL로 작성한 쿼리문을 넣는다. from 뒤에는 엔티티 클래스로 작성한 Item을
    지정해주고 Item으로부터 데이터를 select하겠다는 것을 의미함.
    파라미터에 @Param 어노테이션을 이용하여 파라미터로 넘어온 값을 JPQL에 들어갈 변수로 지정해줄 수 있다
    아래 코드는 itemDetail 변수를 "like % %"사이에 ":itemDetail"로 값이 들어가도록 작성함

    @Param 어노테이션을 이용하여 변수를 JPQL 에 전달하는 대신 파라미터의 순서를 이용해 전달해줄 수 있다.
    ":itemDetail"대신 첫 번째 파라미터를 전달하겠다는 "?1"이라는 표현을 사용할 수 있지만
    파라미터 순서가 변하면 동작하지 않을 수 있으므로 명시적은 방법을 사용하는게 좋다.

    value 안에 네이티브 쿼리문을 작성하고 "nativeQuery=true"를 지정한다.
    96.p 추가 설명
     */
    @Query(value = "select * from ITEM i where i.ITEM_DETAIL like" +
            "%:itemDetail% order by i.PRICE desc ", nativeQuery = true)
    List<ItemEntity> findByItemDetail(@Param("itemDetail") String itemDetail);

}
