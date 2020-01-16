package org.ct.seckill.controller;

import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.domain.User;
//import org.ct.seckill.redis.RedisService;
import org.ct.seckill.dto.GoodsDto;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.redis.UserKey;
import org.ct.seckill.result.MsgCode;
import org.ct.seckill.result.Result;
import org.ct.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/test")
public class SampleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(path = "/demo")
    public String testDemo(Model model) {
        model.addAttribute("demo", "测试");
        return "test";
    }

    @RequestMapping(path = "/user")
    @ResponseBody
    public List<User> testUser() {
        User user = new User();
        user.setId(1);
        return userService.getUserList(user);
    }

    @RequestMapping(path = "/tra")
    public String testTra() {
        userService.getAccount();
        return "test";
    }

    @RequestMapping(path = "/set")
    public void setRedis() {
        User user = new User();
        user.setId(1);
        user.setName("haha");
        redisService.set(UserKey.getById, "1", user);
    }

    @RequestMapping(path = "/get")
    @ResponseBody
    public Result getRedis() {
        User user = redisService.get(UserKey.getById, "1", User.class);
        return new Result().success(user);
    }

    @ResponseBody
    @RequestMapping(path = "/error")
    public Result testError() {
        return new Result<>().error(MsgCode.ERROR_ORDER);
    }

    @RequestMapping(path = "/to_detail", method = RequestMethod.GET)
    @ResponseBody
    public <T> Result<T> toGoodsDetail(MiaoshaUser miaoshaUser) {
        return (Result<T>) new Result<T>().success(miaoshaUser);
    }

}
