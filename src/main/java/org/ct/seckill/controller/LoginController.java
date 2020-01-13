package org.ct.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.domain.MiaoshaUser;
import org.ct.seckill.dto.LoginDto;
import org.ct.seckill.redis.MiaoshaUserKey;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.result.MsgCode;
import org.ct.seckill.result.Result;
import org.ct.seckill.service.IMiaoshaUserService;
import org.ct.seckill.util.UUIDUtil;
import org.ct.seckill.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 处理登录信息控制器
 */
@Controller
@RequestMapping(path = "/login")
@Slf4j
public class LoginController {

    @Autowired
    private IMiaoshaUserService miaoshaUserService;


    /**
     * 将页面跳转到登录界面
     *
     * @return
     */
    @RequestMapping
    public String toLogin() {
        return "login";
    }

    /**
     * 处理登录请求
     *
     * @param loginDto
     * @return
     */
    @RequestMapping(path = "/doLogin")
    @ResponseBody
    public Result doLogin(@Valid LoginDto loginDto, HttpServletResponse response) {
        //验证用户名与密码
        MiaoshaUser userExists = miaoshaUserService.isUserExists(response, loginDto);
        if (userExists != null) {
            return new Result().success(userExists);
        }

        return new Result().error(MsgCode.ERROR_UNKOWN);
    }

}
