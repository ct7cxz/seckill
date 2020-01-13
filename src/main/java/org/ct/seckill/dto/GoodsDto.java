package org.ct.seckill.dto;

import lombok.Data;
import org.ct.seckill.domain.Goods;

import java.util.Date;

@Data
public class GoodsDto extends Goods {

    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

}
