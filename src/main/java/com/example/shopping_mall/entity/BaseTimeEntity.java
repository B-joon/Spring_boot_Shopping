package com.example.shopping_mall.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
// 공통 매핑 정보가 필요할 때 사용하는 어노테이션으로 부모 클래스를 상속 받는
// 자식 클래스에 매핑 정보만 제공한다.
@MappedSuperclass
@Getter @Setter
public class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false, name = "REG_TIME")
    private LocalDateTime regTime;

    @LastModifiedDate
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

}