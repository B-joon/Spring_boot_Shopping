package com.example.shopping_mall.service;

import com.example.shopping_mall.entity.ItemEntity;
import com.example.shopping_mall.vo.ItemFormVO;
import com.example.shopping_mall.vo.ItemSearchVO;
import com.example.shopping_mall.vo.MainItemVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {

    public long saveItem(ItemFormVO itemFormVO, List<MultipartFile> itemImgFileList) throws Exception;

    public ItemFormVO getItemDtl(Long itemId);

    public Long updateItem(ItemFormVO itemFormVO, List<MultipartFile> itemImgFileList) throws Exception;

    public Page<ItemEntity> getAdminItemPage(ItemSearchVO itemSearchVO, Pageable pageable);

    public Page<MainItemVO> getMainItemPage(ItemSearchVO itemSearchVO, Pageable pageable);

}
