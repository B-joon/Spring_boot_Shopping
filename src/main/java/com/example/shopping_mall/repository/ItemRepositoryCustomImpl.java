package com.example.shopping_mall.repository;

import com.example.shopping_mall.constant.ItemSellStatus;
import com.example.shopping_mall.entity.ItemEntity;
import com.example.shopping_mall.entity.QItemEntity;
import com.example.shopping_mall.entity.QItemImgEntity;
import com.example.shopping_mall.vo.ItemSearchVO;
import com.example.shopping_mall.vo.MainItemVO;
import com.example.shopping_mall.vo.QMainItemVO;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

    // 동적으로 쿼리를 생성하기 위해서 JPAQueryFactory 클래스를 사용
    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
        return searchSellStatus == null ? null : QItemEntity.itemEntity.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }
        return QItemEntity.itemEntity.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {

        if (StringUtils.equals("itemNm", searchBy)) {
            return QItemEntity.itemEntity.itemNm.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("createdBy", searchBy)) {
            return QItemEntity.itemEntity.createdBy.like("%" + searchQuery + "%");
        }
        return null;
    }

    @Override
    public Page<ItemEntity> getAdminItemPage(ItemSearchVO itemSearchVO, Pageable pageable) {

        QueryResults<ItemEntity> results = queryFactory.selectFrom(QItemEntity.itemEntity)
                .where(regDtsAfter(itemSearchVO.getSearchDateType()),
                        searchSellStatusEq(itemSearchVO.getSearchSellStatus()),
                        searchByLike(itemSearchVO.getSearchBy(),
                                itemSearchVO.getSearchQuery())).
                orderBy(QItemEntity.itemEntity.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ItemEntity> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression itemNmLike(String searchQuery) {
        return StringUtils.isEmpty(searchQuery) ? null
                : QItemEntity.itemEntity.itemNm.like("%" + searchQuery + "%");
    }

    @Override
    public Page<MainItemVO> getMainItemPage(ItemSearchVO itemSearchVO, Pageable pageable) {

        QItemEntity item = QItemEntity.itemEntity;
        QItemImgEntity itemImg = QItemImgEntity.itemImgEntity;

        QueryResults<MainItemVO> results = queryFactory
                .select(
                    new QMainItemVO(
                            item.id,
                            item.itemNm,
                            item.itemDetail,
                            itemImg.imgUrl,
                            item.price)
                    )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repImgYn.eq("Y"))
                .where(itemNmLike(itemSearchVO.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainItemVO> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
