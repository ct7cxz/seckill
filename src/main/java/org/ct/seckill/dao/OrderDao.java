package org.ct.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.ct.seckill.domain.MiaoshaOrder;
import org.ct.seckill.domain.OrderInfo;
import org.mapstruct.Mapper;

@Mapper
public interface OrderDao {

    MiaoshaOrder findOrderByUser(@Param("mId")Long mId, @Param("gId") Long gId);

    void createOrder(OrderInfo orderInfo);
}
