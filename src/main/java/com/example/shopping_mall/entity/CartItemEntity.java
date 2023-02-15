package com.example.shopping_mall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CART_ITEM")
@Getter @Setter
public class CartItemEntity extends BaseEntity{

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

    public static CartItemEntity createCartItem(CartEntity cart, ItemEntity item, int count) {
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);

        return cartItem;
    }

    // 장바구니에 기존에 담겨 있는 상품인데, 해당 상품을 추가로 장바구니에 담을 때
    // 기존 수량에 현재 담을 수량을 더해줄 때 사용할 메소드
    public void addCount(int count) {
        this.count += count;
    }

    public void updateCount(int count) {
        this.count = count;
    }

}
