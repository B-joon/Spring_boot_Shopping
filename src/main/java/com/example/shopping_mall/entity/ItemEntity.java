package com.example.shopping_mall.entity;

import com.example.shopping_mall.constant.ItemSellStatus;
import com.example.shopping_mall.exception.OutOfStockException;
import com.example.shopping_mall.vo.ItemFormVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ITEM")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemEntity extends BaseEntity{

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

    @Column(name = "ITEM_SELL_STATUS")
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  // 상품 판매 상태

    @Column(name = "REG_TIME")
    private LocalDateTime regTime;          // 등록 시간

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;       // 수정 시간

    public void updateItem(ItemFormVO itemFormVO) {
        this.itemNm = itemFormVO.getItemNm();
        this.price = itemFormVO.getPrice();
        this.stockNumber = itemFormVO.getStockNumber();
        this.itemDetail = itemFormVO.getItemDetail();
        this.itemSellStatus = itemFormVO.getItemSellStatus();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber) {
        this.stockNumber += stockNumber;
    }

}
