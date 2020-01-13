package org.ct.seckill.dao;

import org.ct.seckill.dto.GoodsDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 商品详情页的持久层
 */

@Mapper
public interface GoodsDao {
    List<GoodsDto> getGoodsDtoList();

    GoodsDto findGoodsById(GoodsDto goodsDto);
}
