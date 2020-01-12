package org.ct.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.dto.LoginDto;
import org.ct.seckill.result.MsgCode;
import org.ct.seckill.result.Result;
import org.ct.seckill.service.IMiaoshaUserService;
import org.ct.seckill.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Result doLogin(LoginDto loginDto) {
        System.out.println("客户端传递的密码"+loginDto.getPassword());
        if (!ValidationUtil.isMobile(loginDto.getMobile())) {
            return new Result().error(MsgCode.ERROR_MOBILE);
        }
        if (loginDto.getPassword() == null || "".equals(loginDto.getPassword())) {
            return new Result().error(MsgCode.ERROR_PASSWORD);
        }
        MsgCode userExists = miaoshaUserService.isUserExists(loginDto);
        if (userExists.getCode() == 200) {
            return new Result().success("登录成功!");
        } else {
            return new Result().error(userExists);
        }
    }

}
