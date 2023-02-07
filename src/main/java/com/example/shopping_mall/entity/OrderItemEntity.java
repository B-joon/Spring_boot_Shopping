package com.example.shopping_mall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class OrderItemEntity extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    /*
    FetchType.LAZY
    성능 문제가 있을 수 있기 때문에 지연 로딩 사용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private ItemEntity item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    @Column(name = "ORDER_PRICE")
    private int orderPrice;

    @Column(name = "COUNT")
    private int count;

}
