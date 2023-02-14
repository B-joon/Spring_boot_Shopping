package com.example.shopping_mall.service;

import com.example.shopping_mall.vo.OrderHistVO;
import com.example.shopping_mall.vo.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    public Long Order(OrderVO orderVO, String email);

    public Page<OrderHistVO> getOrderList(String email, Pageable pageable);

    public boolean validateOrder(Long orderId, String email);

    public void cancleOrder(Long orderId);

    public Long orders(List<OrderVO> orderVOList, String email);

}
