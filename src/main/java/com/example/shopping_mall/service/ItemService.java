package com.example.shopping_mall.service;

import com.example.shopping_mall.vo.ItemFormVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {

    public long saveItem(ItemFormVO itemFormVO, List<MultipartFile> itemImgFileList) throws Exception;

    public ItemFormVO getItemDtl(Long itemId);

    public Long updateItem(ItemFormVO itemFormVO, List<MultipartFile> itemImgFileList) throws Exception;

}
