package com.example.shopping_mall.entity;

import com.example.shopping_mall.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ITEM")
@Getter
@Setter
@ToString
public class ItemEntity {

    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            // 상품코드

    @Column(name = "ITEM_NAME", nullable = false, length = 50)
    private String itemNm;      // 상품명

    @Column(name = "PRICE", nullable = false)
    private int price;          // 가격

    @Column(name = "STOCK_NUMBER", nullable = false)
    private int stockNumber;    // 재고수량

    @Lob
    @Column(name = "ITEM_DETAIL", nullable = false)
    private String itemDetail;  // 삼품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  // 상품 판매 상태

    private LocalDateTime regTime;          // 등록 시간

    private LocalDateTime updateTime;       // 수정 시간

}
