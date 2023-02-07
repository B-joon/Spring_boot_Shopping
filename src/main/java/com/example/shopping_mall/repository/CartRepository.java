package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
