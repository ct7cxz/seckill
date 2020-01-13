package org.ct.seckill.service;

import org.ct.seckill.dto.GoodsDto;

import java.util.List;

public interface IGoodsService {
    List<GoodsDto> getGoodsDtoList();

    GoodsDto findGoodsById(GoodsDto goodsDto);
}
