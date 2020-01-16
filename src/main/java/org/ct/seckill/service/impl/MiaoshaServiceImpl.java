package org.ct.seckill.service.impl;

import org.ct.seckill.dao.MiaoshaDao;
import org.ct.seckill.domain.MiaoshaGoods;
import org.ct.seckill.domain.MiaoshaOrder;
import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.domain.OrderInfo;
import org.ct.seckill.dto.GoodsDto;
import org.ct.seckill.service.IGoodsService;
import org.ct.seckill.service.IMiaoshaService;
import org.ct.seckill.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单详情页面
 */
@Service
public class MiaoshaServiceImpl implements IMiaoshaService {

    @Autowired
    private MiaoshaDao miaoshaDao;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IOrderService orderService;

    /**
     * 减库存 下订单 将订单写入到数据库
     * @param miaoshaUser
     * @param goodsDto
     * @return
     */
    @Override
    @Transactional
    public OrderInfo insertMiaoshaOrder(MiaoshaUser miaoshaUser, GoodsDto goodsDto) {
        //减少商品库存
        Integer update = goodsService.updateMiaoshaGoods(goodsDto);

        //下订单 将订单写入到库存当中
        if(update == 1) {
            return orderService.createOrder(miaoshaUser, goodsDto);
        }
        return null;
    }

    /**
     * 创建新的秒杀用户订单
     * @param miaoshaOrder
     */
    @Override
    public void createMiaoshaOrder(MiaoshaOrder miaoshaOrder) {
        miaoshaDao.createMiaoshaOrder(miaoshaOrder);
    }
}
