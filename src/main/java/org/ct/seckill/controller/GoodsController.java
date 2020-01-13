package org.ct.seckill.controller;

import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.dto.GoodsDto;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.service.IGoodsService;
import org.ct.seckill.service.IMiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(path = "/goods")
public class GoodsController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IMiaoshaUserService miaoshaUserService;

    @Autowired
    private IGoodsService goodsService;

    /**
     * 判断登录后是否保存了cookie，若都没有保存，从cookie中获取token
     * 若登录了，从缓存中获取登录的用户信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/to_list")
    public String toGoodsList(Model model, MiaoshaUser miaoshaUser) {
        model.addAttribute("miaoshaUser", miaoshaUser);

        List<GoodsDto> goodsDtoList = goodsService.getGoodsDtoList();
        model.addAttribute("goodsList", goodsDtoList);
        return "goods_list";
    }

    /**
     * 跳转到商品的详情页面
     *
     * @param goodsDto
     * @return
     */
    @RequestMapping(path = "/to_detail/{id}", method = RequestMethod.GET)
    public String toGoodsDetail(GoodsDto goodsDto, Model model, MiaoshaUser miaoshaUser) {
        GoodsDto findGoodsDto = goodsService.findGoodsById(goodsDto);
        model.addAttribute("goods", findGoodsDto);
        model.addAttribute("miaoshaUser", miaoshaUser);

        //获取秒杀的开始和结尾时间，判断秒杀是否开始
        long startTime = findGoodsDto.getStartDate().getTime();
        long endTime = findGoodsDto.getEndDate().getTime();
        long nowTime = System.currentTimeMillis();

        int miaoshaStaus;
        int miaoshaCountdown;
        int countdownStart =0;
        //0 表示秒杀尚未开始，1表示秒杀正在进行中，2表示秒杀已经结束
        if (nowTime < startTime) {
            countdownStart = (int) (startTime - nowTime);
            miaoshaCountdown = 0;
            miaoshaStaus = 0;
        } else if (nowTime > startTime && nowTime < endTime) {
            miaoshaStaus = 1;
            miaoshaCountdown = (int) (endTime - nowTime) /1000;
        } else {
            miaoshaStaus = 2;
            miaoshaCountdown = 0;
        }

        model.addAttribute("countdownStart",countdownStart);
        model.addAttribute("miaoshaStaus", miaoshaStaus);
        model.addAttribute("miaoshaCountdown", miaoshaCountdown);
        return "goods_detail";
    }
}
