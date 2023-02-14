package com.example.shopping_mall.vo;

import com.example.shopping_mall.constant.OrderStatus;
import com.example.shopping_mall.entity.OrderEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class OrderHistVO {

    public OrderHistVO(OrderEntity order) {
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    private Long orderId;               // 주문아이디
    private String orderDate;           // 주문날짜
    private OrderStatus orderStatus;    // 주문상태
    private List<OrderItemVO> orderItemVOList = new ArrayList<>();

    public void addOrderItemVO(OrderItemVO orderItemVO){
        orderItemVOList.add(orderItemVO);
    }

}
