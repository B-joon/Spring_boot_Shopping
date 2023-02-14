package com.example.shopping_mall.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "CART")
@Getter @Setter
@ToString
public class CartEntity extends BaseEntity{

    @Id
    @Column(name = "CART_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * OneToOne 어노테이션을 이용해 회원 엔티티와 일대일로 매핑을 함
     * fetch = FetchType.EAGER
     * 해당 엔티티와 매핑된 엔티티도 한 번에 조회하는 것을 '즉시 로딩'이라고 함
     * 따로 옵션을 주지 않으면 즉시 로딩으로 설정 됨.
     */
    @OneToOne(fetch = FetchType.LAZY)
    /**
     * JoinColumn 어노테이션을 이용해 매핑할 외래키를 지정
     * name 속성에는 매핑할 외래키의 이름을 설정
     * name를 명시하지 않으면 JPA가 알아서 ID를 찾지만 컬럼명이 원하는 대로 생성되지 않을 수 있기 때문에 지정
     */
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity member;

    public static CartEntity createCart(MemberEntity member) {

        CartEntity cart = new CartEntity();
        cart.setMember(member);

        return cart;
    }

}
