package org.ct.seckill.controller;

import org.ct.seckill.domain.User;
//import org.ct.seckill.redis.RedisService;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        redisService.set("keys", user);
    }

    @RequestMapping(path = "/get")
    @ResponseBody
    public User getRedis(){
        User user = redisService.get("keys", User.class);
        System.out.println(user);
        return user;
    }
}
