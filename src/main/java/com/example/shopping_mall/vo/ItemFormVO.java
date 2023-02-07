package com.example.shopping_mall.vo;

import com.example.shopping_mall.constant.ItemSellStatus;
import com.example.shopping_mall.entity.ItemEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
public class ItemFormVO {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotBlank(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String itemDetail;

    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    // 상품 저장 후 수정할 때 상품 이미지 정보를 저장하는 리스트
    private List<ItemImgVO> itemImgVOList = new ArrayList<>();

    // 상품의 이미지 아이디를 저장하는 리스트
    // 상품 등록 시에는 아직 상품의 이미지를 저장하지 않았기 때문에
    // 아무값도 들어가 있지 않고 수정 시에 이미지 아이디를 담아둘 용도로 사용
    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    /*
    createItem, of
    modelMapper를 이용하여 엔티티 객체와 DTO 객체 간의 데이터를 복사하여
    복사한 객체를 반환해주는 메소드
     */
    public ItemEntity createItem() {
        return modelMapper.map(this, ItemEntity.class);
    }

    public static ItemFormVO of(ItemEntity item) {
        return modelMapper.map(item, ItemFormVO.class);
    }

}
