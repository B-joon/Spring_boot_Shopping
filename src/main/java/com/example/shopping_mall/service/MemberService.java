package com.example.shopping_mall.service;

import com.example.shopping_mall.entity.MemberEntity;

public interface MemberService {

    public MemberEntity save(MemberEntity member);
    public void validateDuplicateMember(MemberEntity member);

}
