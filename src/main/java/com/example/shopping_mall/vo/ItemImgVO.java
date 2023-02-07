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

    public static ItemImgVO of(ItemImgEntity itemImg) {
        return modelMapper.map(itemImg, ItemImgVO.class);
    }

}
