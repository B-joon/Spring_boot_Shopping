package com.example.shopping_mall.service;

import com.example.shopping_mall.entity.ItemImgEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ItemImgService {

    public void saveItemImg(ItemImgEntity itemImg, MultipartFile itemImgFile) throws Exception;

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception;

}
