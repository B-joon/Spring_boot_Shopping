package com.example.shopping_mall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_IMG")
@Getter @Setter
public class ItemImgEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_IMG_ID")
    private Long id;

    @Column(name = "IMG_NAME")
    private String imgName;             // 이미지 파일명

    @Column(name = "ORI_IMG_NAME")
    private String oriImgName;          // 원본 이미지 파일명

    @Column(name = "IMG_URL")
    private String imgUrl;              // 이미지 조회 경로

    @Column(name = "REP_IMG_YN")
    private String repImgYn;            // 댜표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private ItemEntity item;

    /**
     *
     * @param oriImgName
     * @param imgName
     * @param imeUrl
     *
     * 원본 이미지 파일명, 업데이트할 이미지 파일명, 이미지 경로를 파라미터로
     * 입력 받아서 이미지 정보를 업데이트 하는 메소드
     *
     */
    public void updateItemImg(String oriImgName, String imgName, String imeUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imeUrl;
    }

}
