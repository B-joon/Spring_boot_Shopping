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

/*
Item 클래스를 entity로 선언합니다. 또한 @Table 어노테이션을 통해 어떤 테이블과 매핑될지를 정합니다.
ITEM 테이블과 매핑되도록 name을 ITEM으로 지정해야 합니다.
 */
@Entity
@Table(name = "ITEM")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemEntity extends BaseEntity{

    /*
    entity로 선언한 클래스는 반드시 기본키를 가져야 한다.
    기본키가 되는 맴버변수에 @Id 어노테이션을 붙여준다.
    테이블에 매핑될 컬럼의 이름을 @Column 어노테이션을 툉해 설정해준다.
    Item 클래스의 id 변수와 Item 테이블의 item_id 컬럼이 매핑되도록 한다.
    @GeneratedValue 어노테이션을 통해 기본키 생성 전ㄺ을 AUTO로 지정
     */
    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            // 상품코드

    /*
    @Column 어노테이션의 nullable 속성을 이용해서 항상 값이 있어야 하는 필드는 not null 설정한다.
    String 필드는 default 값으로 255가 설정돼 있다.
    각 String 필드마다 필요한 길이를 length 속성에 default 값을 세팅한다.
     */
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
