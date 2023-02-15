package com.example.shopping_mall.repository;

import com.example.shopping_mall.constant.ItemSellStatus;
import com.example.shopping_mall.entity.ItemEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        ItemEntity item = new ItemEntity();
        item.setItemNm("테스트 상품");
        item.setItemDetail("테스트 상품 상세 설명");
        item.setPrice(100000);
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        ItemEntity savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }

    public void createItemList() {
        for (int i = 0; i < 10; i++) {
            ItemEntity item = new ItemEntity();
            item.setItemNm("테스트 상품"+i);
            item.setItemDetail("테스트 상품 상세 설명"+i);
            item.setPrice(10000+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            ItemEntity savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest() {
        this.createItemList();
        List<ItemEntity> itemList = itemRepository.findByItemNm("테스트 상품2");
        for (ItemEntity item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByItemNmOrItemDetailTest() {
        this.createItemList();
        List<ItemEntity> itemList = itemRepository
                .findByItemNmOrItemDetail("테스트 상품3", "테스트 상품 상세 설명5");
        for (ItemEntity item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThen 테스트")
    public void findByPriceLessThenTest() {
        this.createItemList();
        List<ItemEntity> itemList = itemRepository
                .findByPriceLessThan(10003);
        for (ItemEntity item : itemList) {
            System.out.println(item.toString());
        }
    }
}