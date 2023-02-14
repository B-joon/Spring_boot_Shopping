package com.example.shopping_mall.vo;

import com.example.shopping_mall.entity.OrderItemEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemVO {

    public OrderItemVO(OrderItemEntity orderItem, String imgUrl) {
        this.itemNm = orderItem.getItem().getItemNm();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
    }

    private String itemNm;
    private int count;
    private int orderPrice;
    private String imgUrl;

}
