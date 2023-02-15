package com.example.shopping_mall.vo;

import com.example.shopping_mall.entity.ItemImgEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ItemImgVO {

    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;
    private static ModelMapper modelMapper = new ModelMapper();

    // ItemImgEntity 객체를 파라미터로 받아 ItemImgEntity 객체의 자료형과 맴버변수의
    // 이름이 같을 때 ItemImgVO로 값을 복사해서 반환한다.
    // static 메소드로 선언해 ItemImgVO 객체를 생성하지 않아도 호출할 수 있도록 한다.
    public static ItemImgVO of(ItemImgEntity itemImg) {
        return modelMapper.map(itemImg, ItemImgVO.class);
    }

}
