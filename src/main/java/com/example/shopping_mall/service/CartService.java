package com.example.shopping_mall.service;

import com.example.shopping_mall.vo.CartDetailVO;
import com.example.shopping_mall.vo.CartItemVO;
import com.example.shopping_mall.vo.CartOrderVO;

import java.util.List;

public interface CartService {

    public Long addCart(CartItemVO cartItemVO, String email);

    public List<CartDetailVO> getCartList(String email);

    public boolean validateCartItem(Long cartItemId, String email);

    public void updateCartItemCount(Long cartItemId, int count);

    public void deleteCartItem(Long cartItemId);

    public Long orderCartItem(List<CartOrderVO> cartOrderVOList, String email);

}
