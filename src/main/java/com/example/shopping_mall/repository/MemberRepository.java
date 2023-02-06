package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByEmail(String email);

}
