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

    /*
    @Enumerated 자바의 enum타입을 엔티티의 속성으로 지정할 수 있다.
    Enum을 사용할 때 기본적으로 순서가 저장되는데 Enum의 순서가 바뀔 경우 발생할 수 있으므로
    "EnumType.STRING" 옵션을 사용해서 String으로 저장한다.
     */
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
