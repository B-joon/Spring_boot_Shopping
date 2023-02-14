package com.example.shopping_mall.entity;

import com.example.shopping_mall.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter @Setter
public class OrderEntity {

    @Id
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity member;

    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;        // 주문일

    @Column(name = "ORDER_STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;        // 주문상태

    /*
    속성의 값으로 "order"를 적어준 이유는 OrderItemEntity에 있는
    OrderEntity에 의해 관리된다는 의미로 해성하시면 합니다.

    CascadeType.ALL
    부모 엔티티의 영속성 상태 변화를 자식 엔티티에 모두 전이하는 옵션

    orphanRemoval = true
    부모 엔티티에서 자식 엔티티를 삭제했을 때 orderItemEntity를 삭제하는 옵션
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    @Column(name = "REG_TIME")
    private LocalDateTime regTime;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    public void addOrderItem(OrderItemEntity orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static OrderEntity createOrder(MemberEntity member, List<OrderItemEntity> orderItemList) {
        OrderEntity order = new OrderEntity();
        order.setMember(member);

        for (OrderItemEntity orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }

        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for (OrderItemEntity orderItem: orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }

    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;

        for (OrderItemEntity orderItem : orderItems) {
            orderItem.cancel();
        }
    }

}
