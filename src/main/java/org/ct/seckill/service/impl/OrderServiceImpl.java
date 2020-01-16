package org.ct.seckill.service.impl;

import com.google.common.collect.Ordering;
import org.ct.seckill.dao.OrderDao;
import org.ct.seckill.domain.MiaoshaOrder;
import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.domain.OrderInfo;
import org.ct.seckill.dto.GoodsDto;
import org.ct.seckill.service.IMiaoshaService;
import org.ct.seckill.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 订单详情页业务层
 */

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private IMiaoshaService miaoshaService;

    /**
     * 根据用户id和订单id，查询当前用户是否已经秒杀郭
     * @param miaoshaUser
     * @param goodsDto
     * @return 秒杀过则返回订单详情信息
     */
    @Override
    public MiaoshaOrder findOrderByUser(MiaoshaUser miaoshaUser, GoodsDto goodsDto) {
        return orderDao.findOrderByUser(miaoshaUser.getId(),goodsDto.getId());
    }

    /**
     * 创建订单详情信息 和 秒杀详情信息
     * @param miaoshaUser
     * @param goodsDto
     * @return
     */
    @Override
    @Transactional
    public OrderInfo createOrder(MiaoshaUser miaoshaUser, GoodsDto goodsDto) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setGoodsId(goodsDto.getId());
        orderInfo.setUserId(miaoshaUser.getId());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsName(goodsDto.getGoodsName());
        orderInfo.setGoodsPrice(goodsDto.getGoodsPrice());
        orderInfo.setCreateDate(new Date());
        orderInfo.setStatus(0);
        //创建秒杀详情订单
        orderDao.createOrder(orderInfo);

        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setUserId(miaoshaUser.getId());
        miaoshaOrder.setGoodsId(goodsDto.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        //创建秒杀订单
        miaoshaService.createMiaoshaOrder(miaoshaOrder);

        return orderInfo;
    }

}
