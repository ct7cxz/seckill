package org.ct.seckill.service;

import org.ct.seckill.domain.MiaoshaOrder;
import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.domain.OrderInfo;
import org.ct.seckill.dto.GoodsDto;

public interface IOrderService {
    MiaoshaOrder findOrderByUser(MiaoshaUser miaoshaUser, GoodsDto goodsDto);

    OrderInfo createOrder(MiaoshaUser miaoshaUser, GoodsDto goodsDto);

}
