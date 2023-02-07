package com.example.shopping_mall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CART_ITEM")
@Getter @Setter
public class CartItemEntity {

    @Id
    @GeneratedValue
    @Column(name = "CART_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private CartEntity cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private ItemEntity item;

    @Column(name = "COUNT")
    private int count;

}
