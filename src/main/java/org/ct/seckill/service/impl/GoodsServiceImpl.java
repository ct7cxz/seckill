package org.ct.seckill.service.impl;

import org.ct.seckill.dao.GoodsDao;
import org.ct.seckill.dto.GoodsDto;
import org.ct.seckill.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 秒杀商品管理的事务层
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 查询所有的商品列表
     * @return
     */
    @Override
    public List<GoodsDto> getGoodsDtoList() {
        return goodsDao.getGoodsDtoList();
    }

    /**
     * 根据商品的编号，查找商品的详情信息
     * @param goodsDto
     * @return
     */
    @Override
    public GoodsDto findGoodsById(GoodsDto goodsDto) {
        return goodsDao.findGoodsById(goodsDto);
    }

    /**
     * 减少商品库存
     * @param goodsDto
     * @return
     */
    @Override
    public Integer updateMiaoshaGoods(GoodsDto goodsDto) {
        return goodsDao.updateMiaoshaGoods(goodsDto);
    }
}
