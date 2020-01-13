package org.ct.seckill.service.impl;

import org.ct.seckill.dao.GoodsDao;
import org.ct.seckill.dto.GoodsDto;
import org.ct.seckill.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<GoodsDto> getGoodsDtoList() {
        return goodsDao.getGoodsDtoList();
    }

    @Override
    public GoodsDto findGoodsById(GoodsDto goodsDto) {
        return goodsDao.findGoodsById(goodsDto);
    }
}
