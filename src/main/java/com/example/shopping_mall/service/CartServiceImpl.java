package com.example.shopping_mall.service;

import com.example.shopping_mall.entity.CartEntity;
import com.example.shopping_mall.entity.CartItemEntity;
import com.example.shopping_mall.entity.ItemEntity;
import com.example.shopping_mall.entity.MemberEntity;
import com.example.shopping_mall.repository.CartItemRepository;
import com.example.shopping_mall.repository.CartRepository;
import com.example.shopping_mall.repository.ItemRepository;
import com.example.shopping_mall.repository.MemberRepository;
import com.example.shopping_mall.vo.CartDetailVO;
import com.example.shopping_mall.vo.CartItemVO;
import com.example.shopping_mall.vo.CartOrderVO;
import com.example.shopping_mall.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    @Override
    public Long addCart(CartItemVO cartItemVO, String email) {

        ItemEntity item = itemRepository.findById(cartItemVO.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        MemberEntity member = memberRepository.findByEmail(email);

        CartEntity cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            cart = CartEntity.createCart(member);
            cartRepository.save(cart);
        }

        CartItemEntity savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if (savedCartItem != null) {
            savedCartItem.addCount(cartItemVO.getCount());
            return savedCartItem.getId();
        } else {
            CartItemEntity cartItem = CartItemEntity.createCartItem(cart, item, cartItemVO.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    @Transactional
    @Override
    public List<CartDetailVO> getCartList(String email) {

        List<CartDetailVO> cartDetailVOList = new ArrayList<>();

        MemberEntity member = memberRepository.findByEmail(email);
        CartEntity cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            return cartDetailVOList;
        }

        cartDetailVOList = cartItemRepository.findCartDetailVOList(cart.getId());

        return cartDetailVOList;
    }

    @Transactional
    @Override
    public boolean validateCartItem(Long cartItemId, String email) {

        MemberEntity curiMember = memberRepository.findByEmail(email);
        CartItemEntity cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        MemberEntity savedMember = cartItem.getCart().getMember();

        if (!StringUtils.equals(curiMember.getEmail(), savedMember.getEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public void updateCartItemCount(Long cartItemId, int count) {
        CartItemEntity cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        CartItemEntity cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public Long orderCartItem(List<CartOrderVO> cartOrderVOList, String email) {

        List<OrderVO> orderVOList = new ArrayList<>();

        for (CartOrderVO cartOrderVO : cartOrderVOList) {
            CartItemEntity cartItem = cartItemRepository.findById(cartOrderVO.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderVO orderVO = new OrderVO();
            orderVO.setItemId(cartItem.getItem().getId());
            orderVO.setCount(cartItem.getCount());
            orderVOList.add(orderVO);
        }

        Long orderId = orderService.orders(orderVOList, email);

        for (CartOrderVO cartOrderVO : cartOrderVOList) {
            CartItemEntity cartItem = cartItemRepository.findById(cartOrderVO.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }

        return orderId;
    }
}
