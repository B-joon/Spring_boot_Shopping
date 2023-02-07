package com.example.shopping_mall.entity;

import com.example.shopping_mall.constant.Role;
import com.example.shopping_mall.vo.MemberVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
@Getter @Setter
@ToString
public class MemberEntity extends BaseEntity{

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(unique = true, name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    public static MemberEntity createMemberUser(MemberVO memberVO, PasswordEncoder passwordEncoder) {

        MemberEntity member = new MemberEntity();

        member.setName(memberVO.getName());
        member.setEmail(memberVO.getEmail());
        member.setAddress(memberVO.getAddress());
        String password = passwordEncoder.encode(memberVO.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);

        return member;
    }

    public static MemberEntity createMemberAdmin(MemberVO memberVO, PasswordEncoder passwordEncoder) {

        MemberEntity member = new MemberEntity();

        member.setName(memberVO.getName());
        member.setEmail(memberVO.getEmail());
        member.setAddress(memberVO.getAddress());
        String password = passwordEncoder.encode(memberVO.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);

        return member;
    }
}
