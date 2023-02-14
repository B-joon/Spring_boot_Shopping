package com.example.shopping_mall.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CartOrderVO {

    private Long cartItemId;
    private List<CartOrderVO> cartOrderVOList;

}
