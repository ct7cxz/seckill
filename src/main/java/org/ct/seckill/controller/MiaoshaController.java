package org.ct.seckill.controller;

import org.ct.seckill.domain.MiaoshaOrder;
import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.domain.OrderInfo;
import org.ct.seckill.dto.GoodsDto;
import org.ct.seckill.result.MsgCode;
import org.ct.seckill.service.IGoodsService;
import org.ct.seckill.service.IMiaoshaService;
import org.ct.seckill.service.IMiaoshaUserService;
import org.ct.seckill.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/miaosha")
public class MiaoshaController {

    @Autowired
    private IMiaoshaService miaoshaService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IOrderService orderService;

    /**
     * 秒杀接口
     *
     * @return
     */
    @RequestMapping(path = "/do_miaosha")
    public String doMiaosha(MiaoshaUser miaoshaUser, GoodsDto goodsDto, Model model, Integer miaoshaCountdown) {
        //判断系统是否还处于秒杀当中
        if (miaoshaCountdown > 0) {
            //判断用户是否登录
            if (miaoshaUser == null) {
                return "login";
            }
            if (goodsDto != null) {
                //判断是否有库存
                GoodsDto findGoods = goodsService.findGoodsById(goodsDto);
                if (findGoods == null || findGoods.getStockCount() <= 0) {
                    model.addAttribute("msg", MsgCode.ERROR_NOT_Goods.getMsg());
                    return "goods_fail";
                }

                //判断用户是否已经有秒杀信息，有则已经秒杀过了，不可重复秒杀
                MiaoshaOrder miaoshaOrder = orderService.findOrderByUser(miaoshaUser, goodsDto);
                if (miaoshaOrder != null) {
                    model.addAttribute("msg", MsgCode.ERROR_HAVE_ORDER.getMsg());
                    return "goods_fail";
                }

                //减库存 下订单 写入到订单
                OrderInfo orderInfo = miaoshaService.insertMiaoshaOrder(miaoshaUser, goodsDto);
                model.addAttribute("orderInfo", orderInfo);
                model.addAttribute("goods",goodsDto);
                return "order_detail";
            } else {
                model.addAttribute("msg", MsgCode.ERROR_REVIEW.getMsg());
                return "goods_fail";
            }
        }else {
            model.addAttribute("msg", MsgCode.ERROR_TIMEOUT.getMsg());
            return "goods_fail";
        }

    }
}
