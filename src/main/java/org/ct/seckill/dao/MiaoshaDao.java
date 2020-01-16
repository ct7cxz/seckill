package org.ct.seckill.dao;

import org.ct.seckill.domain.MiaoshaOrder;
import org.mapstruct.Mapper;

@Mapper
public interface MiaoshaDao {
    void createMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
