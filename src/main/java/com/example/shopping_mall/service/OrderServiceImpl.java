package com.example.shopping_mall.service;

import com.example.shopping_mall.entity.*;
import com.example.shopping_mall.repository.ItemImgRepository;
import com.example.shopping_mall.repository.ItemRepository;
import com.example.shopping_mall.repository.MemberRepository;
import com.example.shopping_mall.repository.OrderRepository;
import com.example.shopping_mall.vo.OrderHistVO;
import com.example.shopping_mall.vo.OrderItemVO;
import com.example.shopping_mall.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;

    @Override
    public Long Order(OrderVO orderVO, String email) {

        ItemEntity item = itemRepository.findById(orderVO.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        MemberEntity member = memberRepository.findByEmail(email);

        List<OrderItemEntity> orderItemList = new ArrayList<>();
        OrderItemEntity orderItem = OrderItemEntity.createOrderItem(item, orderVO.getCount());
        orderItemList.add(orderItem);

        OrderEntity order = OrderEntity.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<OrderHistVO> getOrderList(String email, Pageable pageable) {

        List<OrderEntity> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrder(email);

        List<OrderHistVO> orderHistVOS = new ArrayList<>();

        for (OrderEntity order : orders) {
            OrderHistVO orderHistVO = new OrderHistVO(order);
            List<OrderItemEntity> orderItems = order.getOrderItems();
            for (OrderItemEntity orderItem : orderItems) {
                ItemImgEntity itemImg = itemImgRepository.findByItemIdAndRepImgYn(orderItem.getItem().getId(), "Y");
                OrderItemVO orderItemVO = new OrderItemVO(orderItem, itemImg.getImgUrl());
                orderHistVO.addOrderItemVO(orderItemVO);
            }
            orderHistVOS.add(orderHistVO);
        }

        return new PageImpl<OrderHistVO>(orderHistVOS, pageable, totalCount);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean validateOrder(Long orderId, String email) {

        MemberEntity curMember = memberRepository.findByEmail(email);
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        MemberEntity saveMember = order.getMember();

        if (!StringUtils.equals(curMember.getEmail(), saveMember.getEmail())) {
            return false;
        }

        return true;
    }

    @Override
    public void cancleOrder(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
    }

    @Override
    public Long orders(List<OrderVO> orderVOList, String email) {

        MemberEntity member = memberRepository.findByEmail(email);
        List<OrderItemEntity> orderItemList = new ArrayList<>();

        for (OrderVO orderVO : orderVOList) {
            ItemEntity item = itemRepository.findById(orderVO.getItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderItemEntity orderItem = OrderItemEntity.createOrderItem(item, orderVO.getCount());
            orderItemList.add(orderItem);
        }

        OrderEntity order = OrderEntity.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }
}
