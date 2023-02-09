package com.example.shopping_mall.service;

import com.example.shopping_mall.entity.ItemEntity;
import com.example.shopping_mall.entity.ItemImgEntity;
import com.example.shopping_mall.repository.ItemImgRepository;
import com.example.shopping_mall.repository.ItemRepository;
import com.example.shopping_mall.vo.ItemFormVO;
import com.example.shopping_mall.vo.ItemImgVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    @Override
    public long saveItem(ItemFormVO itemFormVO, List<MultipartFile> itemImgFileList) throws Exception {

        // 상품 등록
        ItemEntity item = itemFormVO.createItem();
        itemRepository.save(item);

        // 이미지 등록
        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImgEntity itemImg = new ItemImgEntity();
            itemImg.setItem(item);
            if (i == 0) {
                itemImg.setRepImgYn("Y");
            } else {
                itemImg.setRepImgYn("N");
            }
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public ItemFormVO getItemDtl(Long itemId) {

        List<ItemImgEntity> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgVO> itemImgVOList = new ArrayList<>();
        for (ItemImgEntity itemImg : itemImgList) {
            ItemImgVO itemImgVO = ItemImgVO.of(itemImg);
            itemImgVOList.add(itemImgVO);
        }

        ItemEntity item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        ItemFormVO itemFormVO = ItemFormVO.of(item);
        itemFormVO.setItemImgVOList(itemImgVOList);

        return itemFormVO;
    }

    @Override
    public Long updateItem(ItemFormVO itemFormVO, List<MultipartFile> itemImgFileList) throws Exception {

        // 상품 수정
        ItemEntity item = itemRepository.findById(itemFormVO.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormVO);

        List<Long> itemImgIds = itemFormVO.getItemImgIds();

        // 이미지 등록
        for (int i = 0; i < itemImgFileList.size(); i++) {
            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
        }

        return item.getId();
    }
}
