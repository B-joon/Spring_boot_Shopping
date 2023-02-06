package com.example.shopping_mall.service;

import com.example.shopping_mall.entity.MemberEntity;
import com.example.shopping_mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public MemberEntity save(MemberEntity member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    @Override
    public void validateDuplicateMember(MemberEntity member) {
        MemberEntity findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
